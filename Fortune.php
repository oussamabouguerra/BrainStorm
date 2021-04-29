<?php

/*

 * File_Fortune: Read and manipulate fortune databases.

 *

 * PHP version 5

 *

 * @category  File_Formats

 * @package   File_Fortune

 * @author    Matthew Weier O'Phinney <mweierophinney@gmail.com>, based on

 *            Fortune.pm, by Greg Ward

 * @copyright 2005 - 2007, Matthew Weier O'Phinney; Fortune.pm v0.2(c) 1999, Greg Ward

 * @version   CVS: $Id: Fortune.php 173 2007-07-24 13:15:24Z matthew $

 * @license   New BSD {@link http://www.opensource.org/licenses/bsd-license.php}

 */



/** File_Fortune_Exception */

require_once 'File/Fortune/Exception.php';



/**

 * File_Fortune

 *

 * File_Fortune: Interface to fortune cookie databases

 *

 * The <em>fortune</em> program is a small but important part of *nix

 * culture, and this package aims to provide support for its "fortune cookie"

 * databases to PHP programmers.

 *

 * @uses      Iterator

 * @uses      Countable

 * @uses      ArrayAccess

 * @package   File_Fortune

 * @version   @release-version@

 * @tutorial  File_Fortune/File_Fortune.cls

 */

class File_Fortune implements Iterator, Countable, ArrayAccess

{

    const VERSION = '1.0.0';



    /**

     * Character that delimits fortunes (defaults to '%')

     * @var string

     */

    public $delim = '%';



    /**

     * Length of longest string in the fortune file

     * @var int

     */

    public $maxLength;



    /**

     * Length of shortest string in the fortune file

     * @var int

     */

    public $minLength;



    /**

     * Version number for fortune file

     * @var int

     */

    public $version;



    /**

     * Flag - has an operation changed the file?

     * @var boolean

     */

    protected $_changed = false;



    /**

     * Current offset (used by iterator)

     * @var int

     */

    protected $_curOffset = 1;



    /**

     * Directory containing fortune files

     * @var string

     */

    protected $_directory;



    /**

     * Fortune file filehandle

     * @var resource

     */

    protected $_file;



    /**

     * Name of fortune file to use

     * @var string

     */

    protected $_filename;



    /**

     * Array of fortune files found in $_directory

     * @var array

     */

    protected $_files;



    /**

     * Bit field for flags

     * @var array

     */

    protected $_flags;



    /**

     * Array of fortunes; unset by default.

     * @var array

     */

    protected $_fortunes;



    /**

     * Name of fortune header file (.dat file)

     * @var string

     */

    protected $_headerFile;



    /**

     * Flag indicating whether or not a header file is present. Defaults to

     * false.

     * @var bool

     */

    protected $_noHeader = false;



    /**

     * Number of strings (fortunes) in the file

     * @var int

     */

    public $numstr;



    /**

     * Array of file offsets (fortune indices)

     * @var array

     */

    protected $offsets;



    /**

     * Constructor

     *

     * Optionally pass a filename or directory name to set the fortune file or

     * directory, and, if passing a fortune file name, optionally pass the name

     * of the header file.

     *

     * @param  string|array$file

     * @param  string $headerFile

     * @return void

     */

    public function __construct($file = null, $headerFile = null)

    {

        if (null !== $file) {

            if (is_array($file)) {

                $this->setFiles($file);

            } elseif (is_dir($file)) {

                $this->setDirectory($file);

            } else {

                $this->setFile($file, $headerFile);

            }

        }

    }



    /**

     * Destructor

     *

     * Close open files

     *

     * @return void

     */

    public function __destruct()

    {

        $this->_close();

    }



    /**

     * Return current fortune in iterator

     *

     * @return string

     * @throws File_Fortune_Exception

     */

    public function current()

    {

        if (!empty($this->_directory) || !empty($this->_files)) {

            throw new File_Fortune_Exception('Iteration over directory or multiple files not permitted');

        }



        if (empty($this->_file)) {

            $this->_open();

        }



        if (is_array($this->_fortunes)) {

            return current($this->_fortunes);

        }



        return $this->offsetGet($this->_curOffset);

    }



    /**

     * Return current iterator key

     *

     * @return int

     * @throws File_Fortune_Exception

     */

    public function key()

    {

        if (!empty($this->_directory) || !empty($this->_files)) {

            throw new File_Fortune_Exception('Iteration over directory or multiple files not permitted');

        }



        if (empty($this->_file)) {

            $this->_open();

        }



        if (is_array($this->_fortunes)) {

            return key($this->_fortunes);

        }



        return $this->_curOffset;

    }



    /**

     * Retrieve next element in iterator

     *

     * @return string|false

     * @throws File_Fortune_Exception

     */

    public function next()

    {

        if (!empty($this->_directory) || !empty($this->_files)) {

            throw new File_Fortune_Exception('Iteration over directory or multiple files not permitted');

        }



        if (empty($this->_file)) {

            $this->_open();

        }



        if (is_array($this->_fortunes)) {

            return next($this->_fortunes);

        }



        ++$this->_curOffset;

        if (!$this->offsetExists($this->_curOffset)) {

            return false;

        }



        return $this->offsetGet($this->_curOffset);

    }



    /**

     * Rewind iterator to first element

     *

     * @return bool

     * @throws File_Fortune_Exception

     */

    public function rewind()

    {

        if (!empty($this->_directory) || !empty($this->_files)) {

            throw new File_Fortune_Exception('Iteration over directory or multiple files not permitted');

        }



        if (empty($this->_file)) {

            $this->_open();

        }



        if (is_array($this->_fortunes)) {

            return reset($this->_fortunes);

        }



        $this->_curOffset = 1;

        return true;

    }



    /**

     * Current element is valid (iterator)

     *

     * @return bool

     * @throws File_Fortune_Exception

     */

    public function valid()

    {

        if (!empty($this->_directory) || !empty($this->_files)) {

            throw new File_Fortune_Exception('Iteration over directory or multiple files not permitted');

        }



        if (empty($this->_file)) {

            $this->_open();

        }



        if (is_array($this->_fortunes)) {

            return key($this->_fortunes) !== null;

        }



        return array_key_exists($this->_curOffset, $this->offsets);

    }



    /**

     * Count of fortunes in current active file, or of all fortunes in all files

     *

     * @return int

     * @throws File_Fortune_Exception

     */

    public function count()

    {

        if (!empty($this->_directory)) {

            $this->_traverseDirectory();

        }

        if (!empty($this->_files)) {

            $count = 0;

            if (!empty($this->_file)) {

                fclose($this->_file);

                $this->_file = null;

            }

            foreach ($this->_files as $file) {

                $this->_filename = $file;

                $this->_open();

                $count += $this->numstr;

                $this->_close();

            }

            return $count;

        }



        if (empty($this->_file)) {

            $this->_open();

        }



        return $this->numstr;

    }



    /**

     * Does fortune exist at the given offset?

     *

     * @param  int $offset

     * @return boolean

     * @throws File_Fortune_Exception

     */

    public function offsetExists($offset)

    {

        if (!empty($this->_directory) || !empty($this->_files)) {

            throw new File_Fortune_Exception('Array access not allowed when directory or multiple files set');

        }



        return $this->_offsetExists($offset);

    }



    /**

     * Retrieve fortune by offset

     *

     * @param  int $offset

     * @return string

     * @throws File_Fortune_Exception

     */

    public function offsetGet($offset)

    {

        if (!empty($this->_directory) || !empty($this->_files)) {

            throw new File_Fortune_Exception('Array access not allowed when directory or multiple files set');

        }



        return $this->_getOne($offset);

    }



    /**

     * Set a fortune at a given offset

     *

     * @param  int $offset

     * @param  string $value

     * @return void

     * @throws File_Fortune_Exception

     */

    public function offsetSet($offset, $value)

    {

        if (!empty($this->_directory) || !empty($this->_files)) {

            throw new File_Fortune_Exception('Array access not allowed when directory or multiple files set');

        }



        $this->_initChanges();

        if (!$this->offsetExists($offset)) {

            throw new File_Fortune_Exception('Cannot modify fortune; offset does not exist');

        }

        $this->_fortunes[$offset - 1] = $value;

    }



    /**

     * Delete a fortune at a given offset

     *

     * @param  int $offset

     * @return void

     * @throws File_Fortune_Exception

     */

    public function offsetUnset($offset)

    {

        if (!empty($this->_directory) || !empty($this->_files)) {

            throw new File_Fortune_Exception('Array access not allowed when directory or multiple files set');

        }



        if (!$this->offsetExists($offset)) {

            throw new File_Fortune_Exception('Cannot delete fortune; offset does not exist');

        }

        $this->_initChanges();

        unset($this->_fortunes[$offset - 1]);

        $this->_fortunes = array_merge(array(), $this->_fortunes);

        $this->numstr = count($this->_fortunes);

    }



    /**

     * Set fortune file

     *

     * Setting a file overwrites any directory set as well.

     *

     * @param  string $file

     * @param  string $headerFile

     * @return File_Fortune

     */

    public function setFile($file, $headerFile = null)

    {

        if ($file != $this->_filename) {

            $this->_close();

        }

        if (!empty($this->_directory)) {

            $this->_directory = null;

        }



        $this->_filename = (string) $file;



        $this->setHeaderFile($headerFile);

        return $this;

    }



    /**

     * Retrieve current fortune file name

     *

     * @return string

     */

    public function getFile()

    {

        return $this->_filename;

    }



    /**

     * Set header file name

     *

     * @param  string $headerFile

     * @return File_Fortune

     */

    public function setHeaderFile($headerFile)

    {

        $this->_headerFile = (string) $headerFile;

        return $this;

    }



    /**

     * Retrieve current header file name

     *

     * @return string

     */

    public function getHeaderFile()

    {

        return $this->_headerFile;

    }



    /**

     * Set one or more files to search for fortunes

     *

     * @return File_Fortune

     * @throws File_Fortune_Exception

     */

    public function setFiles()

    {

        if (0 == func_num_args()) {

            throw new File_Fortune_Exception('setFiles() expects at least one argument');

        }



        $this->_close();

        $this->_directory = null;

        $this->_filename  = null;

        $this->_fortunes  = null;



        $argv = func_get_args();

        $spec = array_shift($argv);

        if (is_array($spec)) {

            $this->_files = $spec;

            return $this;

        }

        $this->_files = array((string) $spec);

        foreach ($argv as $file) {

            $this->_files[] = (string) $file;

        }



        return $this;

    }



    /**

     * Return list of fortune files in use

     *

     * Returns a list of fortune files in use, either as registered via

     * {@link setFiles()} or as determined by traversing the directory set via

     * {@link setDirectory()}.

     *

     * @return array

     */

    public function getFiles()

    {

        if ((null === $this->_files) && !empty($this->_directory)) {

            $this->_traverseDirectory();

        }

        return $this->_files;

    }



    /**

     * Set directory from which to randomly select a fortune file

     *

     * @param  string $directory

     * @return File_Fortune

     */

    public function setDirectory($directory)

    {

        $this->_close();



        $this->_directory = $directory;

        $this->_files     = null;

        $this->_fortunes  = null;

        $this->_filename  = null;



        return $this;

    }



    /**

     * Retrieve current directory of fortune files

     *

     * @return string

     */

    public function getDirectory()

    {

        return $this->_directory;

    }



    /**

     * Add a new fortune

     *

     * @param  string $fortune

     * @return File_Fortune

     * @throws File_Fortune_Exception

     */

    public function add($fortune)

    {

        if (!empty($this->_directory) || !empty($this->_files)) {

            throw new File_Fortune_Exception('Cannot add fortune when directory or multiple files specified');

        }



        $this->_initChanges();

        $this->_fortunes[] = $fortune;

        $this->numstr = count($this->_fortunes);

        return $this;

    }



    /**

     * Update an existing fortune

     *

     * @param  int $index

     * @param  string $fortune

     * @return void

     * @throws File_Fortune_Exception

     */

    public function update($index, $fortune)

    {

        if (!empty($this->_directory) || !empty($this->_files)) {

            throw new File_Fortune_Exception('Cannot update fortune when directory or multiple files specified');

        }



        $this->offsetSet($index, $fortune);

        return $this;

    }



    /**

     * Delete an existing fortune

     *

     * @param  int $index

     * @return void

     * @throws File_Fortune_Exception

     */

    public function delete($index)

    {

        if (!empty($this->_directory) || !empty($this->_files)) {

            throw new File_Fortune_Exception('Cannot delete fortune when directory or multiple files specified');

        }



        $this->offsetUnset($index);

        return $this;

    }



    /**

     * Retrieve random fortune

     *

     * @return string

     */

    public function getRandom()

    {

        if (empty($this->_file) || !empty($this->_directory)) {

            $this->_open();

        }



        if (is_array($this->_fortunes)) {

            $offset = array_rand(array_keys($this->_fortunes));

        } else {

            do {

                $offset = array_rand($this->offsets);

            } while ($offset < 1);

        }



        return $this->_getOne($offset);

    }



    /**

     * Retrieve all fortunes from the current file or set of files

     *

     * @return array

     */

    public function getAll()

    {

        if (!empty($this->_directory)) {

            $this->_traverseDirectory();

        }



        if (!empty($this->_files)) {

            if (!is_array($this->_fortunes)) {

                $fortunes = array();

                if (!empty($this->_file)) {

                    fclose($this->_file);

                    $this->_file = null;

                }

                foreach ($this->_files as $file) {

                    $this->_filename = $file;

                    $fortunes = array_merge($fortunes, $this->_getAll());

                    fclose($this->_file);

                    $this->_file = null;

                }

                $this->_fortunes = $fortunes;

            }

        } else {

            if (!is_array($this->_fortunes)) {

                $this->_fortunes = $this->_getAll();

            }

        }



        return $this->_fortunes;

    }



    /**

     * Save changes

     *

     * @return void

     * @throws File_Fortune_Exception

     */

    public function save()

    {

        if (!empty($this->_directory) || !empty($this->_files)) {

            throw new File_Fortune_Exception('Cannot save changes when directory or multiple files set');

        }



        $filename = $this->getFile();

        $this->_close();

        $this->setFile($filename);

    }



    /**

     * Create a new fortune file from an array of fortunes

     *

     * @param  array $fortunes

     * @param  string $file

     * @return void

     * @throws File_Fortune_Exception

     */

    public function create(array $fortunes, $file = null)

    {

        if ((null !== $file) && ($file != $this->getFile())) {

            $this->setFile($file);

        } else {

            $file = $this->getFile();

        }

        if (null === $file) {

            throw new File_Fortune_Exception('Cannot create fortune file; no file specified');

        }

        if (file_exists($file)) {

            throw new File_Fortune_Exception('Cannot create fortune file; already exists');

        }



        $this->_fortunes = $fortunes;

        $this->_save();

        $this->_close();

    }



    /**

     * Open a fortune file

     *

     * @return void

     * @throws File_Fortune_Exception

     */

    protected function _open()

    {

        if (!empty($this->_file) && empty($this->_directory) && empty($this->_files)) {

            return;

        }



        $this->_close();



        if ($this->_directory || !empty($this->_files)) {

            // get random file from directory or file list

            $this->_getRandomFile();

        }



        if (empty($this->_filename)) {

            throw new File_Fortune_Exception('No file or directory set');

        }



        if (!is_readable($this->_filename)) {

            throw new File_Fortune_Exception('Fortune file "' . $this->_filename . '" not readable');

        }



        if (empty($this->_headerFilename)) {

            $this->setHeaderFile($this->_filename . '.dat');

        }



        if (false === ($this->_file = fopen($this->_filename, 'r'))) {

            throw new File_Fortune_Exception('Unable to read fortune file "' . $this->_filename . '"');

        }



        $this->_readHeader();

    }



    /**

     * Traverse a registered directory to get a list of files

     *

     * @return void

     * @throws File_Fortune_Exception

     */

    protected function _traverseDirectory()

    {

        if (empty($this->_files)) {

            if (!is_dir($this->_directory) || !is_readable($this->_directory)) {

                throw new File_Fortune_Exception('Directory "' . $this->_directory . '" is invalid or unreadable');

            }



            $directory = new DirectoryIterator($this->_directory);

            $files     = array();

            foreach ($directory as $file) {

                if ($file->isDot() || $file->isDir()) {

                    continue;

                }



                $filename = $file->getFilename();

                if ('.dat' == substr($filename, -4)) {

                    continue;

                }



                $files[] = $file->getPathName();

            }

            $this->_files = $files;

        }

    }



    /**

     * Randomly select a fortune file from a registered directory

     *

     * @return void

     */

    protected function _getRandomFile()

    {

        $this->_traverseDirectory();

        $index = array_rand($this->_files);



        $this->_fortunes = null;

        $this->_filename = $this->_files[$index];

    }



    /**

     * Read a fortune database header file

     *

     * Reads the header file associated with this fortune database.  The name of

     * the header file is determined by the {@link __construct() constructor}:

     * either it is based on the name of the data file, or supplied by the

     * caller.

     *

     * If the header file does not exist, this function calls

     * {@link _computeHeader()} automatically, which has the same effect as

     * reading the header from a file.

     *

     * The header contains the following values, which are stored as attributes

     * of the {@link File_Fortune} object:

     * <ul>

     *     <li>{@link $version}; version number</li>

     *     <li>{@link $numstr}; number of strings (fortunes) in the file</li>

     *     <li>{@link $maxLength}; length of longest string in the file</li>

     *     <li>{@link $minLength}; length of shortest string in the file</li>

     *     <li>{@link $flags}; bit field for flags (see strfile(1) man

     *     page)</li>

     *     <li>{@link $delim}; character that delimits fortunes</li>

     * </ul>

     *

     * {@link $numstr} is available via the count() function (count($fortunes));

     * if you're interested in the others, you'll have to go grubbing through

     * the File_Fortune object, e.g.:

     *

     * <code>

     * $fortune_file = new Fortune('fortunes');

     * $delim        = $fortune_file->delim;

     * $maxLength    = $fortune_file->maxLength;

     * </code>

     *

     * @return void

     * @throws File_Fortune_Exception

     */

    protected function _readHeader()

    {

        $headerFile = $this->getHeaderFile();

        if (!file_exists($headerFile)) {

            return $this->_computeHeader();

        }



        if (false === ($file = fopen($headerFile, 'r'))) {

            throw new File_Fortune_Exception('Unable to read header file "' . $headerFile . '"');

        }



        /*

         * from the strfile(1) man page:

         *       unsigned long str_version;  // version number

         *       unsigned long str_numstr;   // # of strings in the file

         *       unsigned long str_longlen;  // length of longest string

         *       unsigned long str_shortlen; // shortest string length

         *       unsigned long str_flags;    // bit field for flags

         *       char str_delim;             // delimiting character

         * that 'char' is padded out to a full word, so the header is 24 bytes

         */



        if (false === ($header = fread($file, 24))) {

            throw new File_Fortune_Exception('Malformed header file "' . $headerFile . '"');

        }



        $values = unpack('N5nums/adelim/xxx', $header);

        if (empty($values) || (6 != count($values))) {

            throw new File_Fortune_Exception('Failed to load full header');

        }



        $this->version    = $values['nums1'];

        $this->numstr     = $values['nums2'];

        $this->maxLength  = $values['nums3'];

        $this->minLength  = $values['nums4'];

        $this->flags      = $values['nums5'];

        $this->delim      = $values['delim'];



        $expectedOffsets = $this->numstr + 1;



        $amountData = 4 * $expectedOffsets;



        if (false === ($data = fread($file, $amountData))) {

            throw new File_Fortune_Exception('Failed to read offsets for all fortunes');

        }

        $offsets = array_merge(unpack('N*', $data));

        if (count($offsets) != $expectedOffsets) {

            throw new File_Fortune_Exception('Expected header offsets do not match actual offsets');

        }

        $this->offsets = $offsets;



        fclose($file);

    }



    /**

     * Compute fortune file header information

     *

     * Reads the contents of the fortune file and computes the header

     * information that would normally be found in a header (.dat) file and read

     * by {@link _readHeader()}.  This is useful if you maintain a file of

     * fortunes by hand and do not have the corresponding data file.

     *

     * An optional delimiter argument may be passed to this function; if

     * present, that delimiter will be used to separate entries in the fortune

     * file.  If not provided, a percent sign ("%") will be used (this is the

     * standard fortune file delimiter).

     *

     * Additionally, the {@link $noHeader} property will be set to true.

     *

     * <b>NOTE:</b> It is most efficient to use fortune files that have header

     * files. In order to get offsets and fortunes, this method actually must

     * read the entire fortune file, and stores all fortunes in the

     * {@link $_fortunes} array.

     *

     * @param  string $delim Defaults to '%'

     * @return void

     * @throws File_Fortune_Exception

     */

    protected function _computeHeader($delim = '%')

    {

        $filename = $this->getFile();

        if (false === ($contents = file_get_contents($filename))) {

            throw new File_Fortune_Exception(

                'Unable to read fortune file (' . $filename . ') to compute headers'

            );

        }



        // Get all fortunes

        // Strip off final delimiter

        $contents = substr_replace($contents, '', strrpos($contents, "$delim\n"));

        $fortunes = explode("$delim\n", $contents); // explode into array



        // Get offsets, min, max, etc.

        $offsets   = array(0);

        $numstr    = count($fortunes);

        $curOffset = 0;

        $delimLen  = strlen($delim);

        $min       = null;

        $max       = null;

        for ($i = 0; $i < $numstr; $i++) {

            $len        = strlen($fortunes[$i]);

            $curOffset += $len + $delimLen + 1;

            $offsets[]  = $curOffset;

            if (empty($min) || ($min > $len)) {

                $min = $len;

            } elseif (empty($max) || ($max < $len)) {

                $max = $len;

            }

        }



        // Set object properties

        $this->version    = 1;

        $this->numstr     = $numstr;

        $this->maxLength  = $max;

        $this->minLength  = $min;

        $this->flags      = 0;

        $this->delim      = $delim;

        $this->offsets    = $offsets;

        $this->_fortunes  = $fortunes;

        $this->noHeader   = true;

    }



    /**

     * Close fortune file

     *

     * Closes the fortune file if it's open, and unsets all header-related

     * properties (except {@link $delim}); does nothing otherwise.

     *

     * @return void

     */

    protected function _close()

    {

        if (!empty($this->_file)) {

            fclose($this->_file);

            $this->_file = null;



            if ($this->_changed) {

                $this->_save();

            }



            unset(

                $this->flags,

                $this->maxLength,

                $this->minLength,

                $this->numstr,

                $this->offsets,

                $this->version

            );



            $this->delim     = '%';

            $this->_changed  = false;

            $this->_fortunes = null;

        }

    }



    /**

     * Read a fortune from the file

     *

     * Reads a fortune directly from the file. If an error occurs, an exception

     * is thrown. Otherwise, on sucess, the fortune is returned.

     *

     * @param int

     * @return string

     * @throws File_Fortune_Exception

     */

    protected function _readFromFile($offset)

    {

        $start  = $this->offsets[$offset - 1];

        $end    = $this->offsets[$offset];

        $length = $end - $start;



        // decrement length 2 bytes for most fortunes (to drop trailing "%\n"),

        // and none for the last one (keep trailing newline)

        $delimLength = strlen($this->delim) | 1;

        $length -= ($offset == $this->numstr) ? 0 : ($delimLength + 2);



        if (-1 == fseek($this->_file, $start)) {

            throw new File_Fortune_Exception('Unable to seek to fortune offset');

        }

        $fortune = fread($this->_file, $length);



        return $fortune;

    }



    /**

     * Initialize changes

     *

     * @return void

     */

    protected function _initChanges()

    {

        $this->_changed = true;

        if (!is_array($this->_fortunes)) {

            $this->getAll();

        }

    }



    /**

     * Save changes to disc

     *

     * Changes to fortunes happen in-memory; this method saves them to disc.

     *

     * @return void

     * @throws File_Fortune_Exception

     */

    protected function _save()

    {

        $this->_initChanges();



        if (!empty($this->_file)) {

            fclose($this->_file);

            $this->_file = null;

        }



        if (false === ($fh = fopen($this->_filename, 'w'))) {

            throw new File_Fortune_Exception(

                'Unable to open "' . $this->filename . '" to write'

            );

        }

        if (!flock($fh, LOCK_EX)) {

            throw new File_Fortune_Exception(

                'Unable to obtain file lock for writing'

            );

        }



        // Write file

        $offsets = array(0); // obtain offsets as we go

        $min     = null;     // For determining shortest fortune

        $max     = null;     // For determining longest fortune

        $numstr  = count($this->_fortunes);

        for ($i = 0; $i < $numstr; ++$i) {

            $fortune = $this->_fortunes[$i];

            fwrite($fh, $fortune);

            if ($i != ($numstr - 1)) {

                fwrite($fh, "\n" . $this->delim . "\n");

            } else {

                fwrite($fh, "\n");

            }

            if (false === ($pos = ftell($fh))) {

                throw new File_Fortune_Exception(

                    'Unable to obtain file position while writing fortunes'

                );

            }



            // Determine if this is the min or max length fortune

            $len     = strlen($fortune);

            if (empty($min) || ($len < $min)) {

                $min = $len;

            }



            if (empty($max) || ($len > $max)) {

                $max = $len;

            }



            // Add offset to list

            $offsets[] = $pos;

        }

        flock($fh, LOCK_UN);

        fclose($fh);



        // Create header block

        // Set version

        if (empty($this->version)) {

            $this->version = 1;

        } else {

            ++$this->version;

        }



        // Set other header attributes

        $this->offsets   = $offsets;         // list of offsets

        $this->numstr    = count($this->_fortunes); // number of fortunes

        $this->maxLength = $max;             // longest fortune

        $this->minLength = $min;             // shortest fortune

        if (empty($this->flags)) {

            $this->flags = 0;

        }



        // Write header file

        $this->_writeHeader();

    }



    /**

     * Write the header file for a fortune file

     *

     * Writes a fortune header file to {@link $headerFilename}, using

     * {@link $version}, {@link $numstr}, {@link $maxLength},

     * {@link $minLength}, {@link $flags}, {@link $delim}, and {@link $offsets}.

     *

     * If an error occurs, an exception is thrown.

     *

     * @return void

     * @throws File_Fortune_Exception

     */

    protected function _writeHeader()

    {

        $headerFile = $this->getHeaderFile();

        if (empty($headerFile)) {

            $headerFile = $this->getFile() . '.dat';

            $this->setHeaderFile($headerFile);

        }



        $header = pack(

            'NNNNNaxxx',

            $this->version,

            $this->numstr,

            $this->maxLength,

            $this->minLength,

            $this->flags,

            $this->delim

        );



        // Pack offsets

        $offsetBin = '';

        foreach ($this->offsets as $offset) {

            $offsetBin .= pack('N', $offset);

        }



        // Write header file

        if (false === ($fh = fopen($headerFile, 'w'))) {

            throw new File_Fortune_Exception(

                'Unable to open header file "' . $headerFile . '" for writing'

            );

        }

        if (!flock($fh, LOCK_EX)) {

            throw new File_Fortune_Exception(

                'Unable to obtain lock on header file'

            );

        }

        fwrite($fh, $header . $offsetBin);

        flock($fh, LOCK_UN);

        fclose($fh);

    }



    /**

     * Retrieve by offset in file

     *

     * @param  int $offset

     * @return boolean

     */

    protected function _offsetExists($offset)

    {

        if (empty($this->_file)) {

            $this->_open();

        }



        if (is_array($this->_fortunes) && isset($this->_fortunes[$offset - 1])) {

            return true;

        } elseif (!is_array($this->_fortunes)

            && isset($this->offsets[$offset])

            && isset($this->offsets[$offset - 1]))

        {

            return true;

        }



        return false;

    }



    /**

     * Retrieve a single fortune by offset

     *

     * @param  int $offset

     * @return string

     * @throws File_Fortune_Exception

     */

    protected function _getOne($offset)

    {

        if ($this->_offsetExists($offset)) {

            if (is_array($this->_fortunes)) {

                return $this->_fortunes[$offset - 1];

            }



            return $this->_readFromFile($offset);

        }



        throw new File_Fortune_Exception('Requested fortune index does not exist');

    }



    /**

     * Retrieve all fortunes from the current active file

     *

     * @return void

     */

    protected function _getAll()

    {

        if (empty($this->_file)) {

            $this->_open();

        }



        $fortunes = array();

        for ($i = 1; $i <= $this->numstr; ++$i) {

            $fortunes[] = $this->_readFromFile($i);

        }




    }

}
