package firstSemester.dictionary;

import firstSemester.dictionary.exceptions.KeyMatchException;
import firstSemester.dictionary.exceptions.ValueMatchException;

/**
 * Interface for dictionary
 *
 * @author Galyautdinov Islam
 * @version interface_v.1
 */
public interface StringIntDictionary {
    /**
     * Method for adding a new value to dictionary
     *
     * @param key   String parameter for special label for value
     * @param value Integer value to add to dictionary
     * @throws IllegalArgumentException The exception that is thrown when the key already exist
     */
    void add(String key, Integer value) throws IllegalArgumentException;

    /**
     * Method for adding a new value to dictionary removing the old value if necessary
     *
     * @param key   String parameter for special label for value
     * @param value Integer value to add to dictionary
     */
    void set(String key, Integer value);

    /**
     * Method for getting a value by its key
     *
     * @param key String parameter for special label for value that need to get
     * @return Integer value of key
     * @throws IllegalArgumentException The exception that is thrown when the key is not exist
     */
    Integer get(String key) throws IllegalArgumentException;

    /**
     * Method for getting a key by its value
     *
     * @param value Integer value whose key to find
     * @return String key of value
     * @throws IllegalArgumentException The exception that is thrown when the value is not exist
     * @throws ValueMatchException      The exception that is thrown when some keys have the same values
     */
    String getKey(Integer value) throws IllegalArgumentException, ValueMatchException, ValueMatchException;

    /**
     * Method for getting a first founded key by its value
     *
     * @param value Integer value whose key to find
     * @return String first key of value
     * @throws IllegalArgumentException The exception that is thrown when the value is not exist
     */
    String getFirstKey(Integer value) throws IllegalArgumentException;

    /**
     * Method for merge two dictionary into one
     *
     * @param dictionary StringIntDictionary-implemented object to be added
     * @return New StringIntDictionary-implemented object with dictionary
     * @throws KeyMatchException The exception that is thrown two objects have the same keys
     */
    StringIntDictionary merge(StringIntDictionary dictionary) throws KeyMatchException, KeyMatchException;

    /**
     * Method for remove the value from dictionary
     *
     * @param key String parameter whose value need to remove
     * @throws IllegalArgumentException The exception that is thrown when the key is not exist
     */
    void remove(String key) throws IllegalArgumentException;

    /**
     * Method for finding the length of dictionary
     *
     * @return Amount of elements
     */
    int size();
}
