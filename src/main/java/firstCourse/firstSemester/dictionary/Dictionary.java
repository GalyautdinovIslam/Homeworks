package firstCourse.firstSemester.dictionary;


import firstCourse.firstSemester.dictionary.exceptions.KeyMatchException;
import firstCourse.firstSemester.dictionary.exceptions.ValueMatchException;

/**
 * Dictionary object
 *
 * @author Galyautdinov Islam
 * @version class_v.1
 */
public class Dictionary implements StringIntDictionary {

    /**
     * Value for dictionary first size(const)
     */
    private final int DICTIONARY_FIRST_SIZE = 1;

    /**
     * Array for keys
     */
    private String[] keys = new String[DICTIONARY_FIRST_SIZE];

    /**
     * Array for key's values
     */
    private Integer[] values = new Integer[DICTIONARY_FIRST_SIZE];

    /**
     * Method for adding a new value to dictionary
     *
     * @param key   String parameter for special label for value
     * @param value Integer value to add to dictionary
     * @throws IllegalArgumentException The exception that is thrown when the key already exists
     */
    @Override
    public void add(String key, Integer value) throws IllegalArgumentException {
        boolean flag = false;
        for (int i = 0; i < this.keys.length; i++) {
            if (key.equals(this.keys[i])) {
                throw new IllegalArgumentException("This key already exists");
            }
            if (this.keys[i] == null) {
                this.keys[i] = key;
                this.values[i] = value;
                flag = true;
                break;
            }
        }
        if (!flag) {
            String[] tmpKeys = new String[this.keys.length + 1];
            Integer[] tmpValues = new Integer[this.keys.length + 1];
            for (int i = 0; i < this.keys.length; i++) {
                tmpKeys[i] = this.keys[i];
                tmpValues[i] = this.values[i];
            }
            tmpKeys[this.keys.length] = key;
            tmpValues[this.keys.length] = value;
            this.keys = tmpKeys;
            this.values = tmpValues;
        }
    }

    /**
     * Method for adding a new value to dictionary removing the old value if necessary
     *
     * @param key   String parameter for special label for value
     * @param value Integer value to add to dictionary
     */
    @Override
    public void set(String key, Integer value) {
        try {
            add(key, value);
        } catch (IllegalArgumentException ex) {
            remove(key);
            add(key, value);
        }
    }

    /**
     * Method for getting a value by its key
     *
     * @param key String parameter for special label for value that need to get
     * @throws IllegalArgumentException The exception that is thrown when the key is not exist
     */
    @Override
    public Integer get(String key) throws IllegalArgumentException {
        for (int i = 0; i < this.keys.length; i++) {
            if (this.keys[i].equals(key)) {
                return this.values[i];
            }
        }
        throw new IllegalArgumentException("Your key is not exist.");
    }

    /**
     * Method for getting a key by its value
     *
     * @param value Integer value whose key to find
     * @throws IllegalArgumentException The exception that is thrown when the value is not exist
     * @throws ValueMatchException      The exception that is thrown when some keys have the same value
     */
    @Override
    public String getKey(Integer value) throws IllegalArgumentException, ValueMatchException {
        String key = "";
        int tmpNumber = -1;
        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i].equals(value)) {
                key = this.keys[i];
                tmpNumber = i;
                break;
            }
        }
        for (int i = tmpNumber + 1; i < this.values.length; i++) {
            if (this.values[i].equals(value)) {
                throw new ValueMatchException("Some keys have the same value. You should use" +
                        " \"getFirstKey(Integer value)\" method.");

                // Будем считать, что это исключение писалось для программиста, т.к. пользователю не обязательно знать
                // про метод getFirstKey(Integer value)
            }
        }
        if (tmpNumber == -1) {
            throw new IllegalArgumentException("Your key is not exist.");
        }
        return key;
    }

    /**
     * Method for getting a first founded key by its value
     *
     * @param value Integer value whose key to find
     * @throws IllegalArgumentException The exception that is thrown when the value is not exist
     */
    @Override
    public String getFirstKey(Integer value) throws IllegalArgumentException {
        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i].equals(value)) {
                return this.keys[i];
            }
        }
        throw new IllegalArgumentException("Your key is not exist.");
    }

    /**
     * Method for merge two dictionary into one
     *
     * @param dictionary StringIntDictionary-implemented object to be added
     * @return New StringIntDictionary-implemented object with dictionary
     * @throws KeyMatchException The exception that is thrown two objects have the same keys
     */
    @Override
    public StringIntDictionary merge(StringIntDictionary dictionary) throws KeyMatchException {
        Dictionary dictionaryForMerge = (Dictionary) dictionary;
        for (int i = 0; i < this.keys.length; i++) {
            if (this.keys[i] == null) {
                continue;
            }
            for (int j = 0; j < dictionaryForMerge.keys.length; j++) {
                if (dictionaryForMerge.keys[j] == null) {
                    continue;
                }
                if (this.keys[i].equals(dictionaryForMerge.keys[j])) {
                    throw new KeyMatchException("This dictionary have the same key.");
                }
            }
        }
        int newDictionarySize = this.size() + dictionaryForMerge.size();
        String[] tmpKeys = new String[newDictionarySize];
        Integer[] tmpValues = new Integer[newDictionarySize];
        int i = 0;
        for (int j = 0; j < this.keys.length; j++) {
            if (this.keys[j] == null) {
                continue;
            }
            tmpKeys[i] = this.keys[j];
            tmpValues[i] = this.values[j];
            i++;
        }
        for (int j = 0; j < dictionaryForMerge.keys.length; j++) {
            if (dictionaryForMerge.keys[j] == null) {
                continue;
            }
            tmpKeys[i] = dictionaryForMerge.keys[j];
            tmpValues[i] = dictionaryForMerge.values[j];
            i++;
        }
        this.keys = tmpKeys;
        this.values = tmpValues;
        return this;
    }

    /**
     * Method for remove the value from dictionary
     *
     * @param key String parameter whose value need to remove
     * @throws IllegalArgumentException The exception that is thrown when the key is not exist
     */
    @Override
    public void remove(String key) throws IllegalArgumentException {
        boolean flag = false;
        for (int i = 0; i < this.keys.length; i++) {
            if (key.equals(this.keys[i])) {
                this.keys[i] = null;
                this.values[i] = null;
                flag = true;
            }
        }
        if (!flag) {
            throw new IllegalArgumentException("This key is not exist.");
        }
    }

    /**
     * Method for finding the length of dictionary
     *
     * @return Amount of elements
     */
    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < this.keys.length; i++) {
            if (this.keys[i] != null) {
                count++;
            }
        }
        return count;
    }
}
