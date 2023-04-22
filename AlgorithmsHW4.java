import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.Object;
import java.io.*;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

import java.lang.Math;

/**
 *
 * @author sidharth
 */
/*
 * The goal of this assignment is to implement a dynamic map (key, value), with
 * positive integer keys and positive integer values.
 * The universe size for the keys is 16777216 (2^24), with the keys being in the
 * range [0, 16777215]
 * 
 * You must implement the following functions:
 * insert (int key, int value) : insert the (key, value) pair. While inserting
 * (K1, V1), if there already exists a key K1 in the map, then replace the
 * existing value with the value V1
 * search (int key) : returns the value corresponding to the key and if the key
 * does not exist then returns -1
 * minimum () : returns the (key value) pair which has the minimal key
 * maximum () : returns the (key value) pair which has the maximum key
 * 
 * The map must be implemented using a Binary search tree and using an augmented
 * direct address table
 * 
 */

class Node {
  public Node(Integer key, Integer value) {
    this.key = key;
    this.value = value;
    left_child = null;
    right_child = null;
  }

  public Integer key;
  public Integer value;
  public Node left_child;
  public Node right_child;
}

/*
 * [100 points]
 * A map implemented via a binary search tree
 */
class map_BST {

  public Node root;

  public map_BST() {
    root = null;
  }

  // [20 Points]
  // return -1 if the key already exists, and you had to perform a replace
  // operation
  // return the key if the key is successfully inserted
  // (Feel free to change the function signature)
  public Node insert(Node node, int key, int value) {
    if (node == null) {
      node = new Node(key, value);

      return node;
    }

    if (node.key < key)
      node.right_child = insert(node.right_child, key, value);
    else if (node.key > key)
      node.left_child = insert(node.left_child, key, value);
    else
      node.value = value;

    return node;
  }

  // [20 Points]
  // return key if the key already exists
  // return -1 if the key does not exist
  // (Feel free to change the function signature)
  public Integer search(Node node, int key) {
    if (node == null)
      return -1;

    if (node.key == key)
      return node.value;

    if (node.key < key)
      return search(node.right_child, key);

    return search(node.left_child, key);
  }

  // [20 Points]
  // return the maximum key-value
  // return null if the hash able is empty
  // (Feel free to change the function signature)
  public Node maximum(Node node) {
    if (node == null)
      return null;

    if (node.right_child != null)
      return maximum(node.right_child);

    return node;
  }

  // [20 Points]
  // return the minimum key-value
  // return null if the hash able is empty
  // (Feel free to change the function signature)
  public Node minimum(Node node) {
    if (node == null)
      return null;

    if (node.left_child != null)
      return minimum(node.left_child);

    return node;
  }

  // [20 Points]
  // Return an array arr of size 2
  // arr[0] the key which has most number of occurence,
  // arr[1] the number of occurence of arr[0]
  public int[] return_key_with_maximum_repetition() {
    int[] arr = new int[2]; // combining both statements in one
    arr[0] = -1;
    arr[1] = 0;

    if (root != null) {
      Queue<Node> queue = new LinkedList<>();
      queue.add(root);

      while (!queue.isEmpty()) {
        Node node = queue.remove();
        if (arr[1] < node.value) {
          arr[0] = node.key;
          arr[1] = node.value;
        }

        if (node.left_child != null)
          queue.add(node.left_child);
        if (node.right_child != null)
          queue.add(node.right_child);
      }
    }

    return arr;
  }
}

/*
 * 100 points
 * 
 * Map is implemented using direct address table of size |U| and a summary array
 * of size |U|^(0.5) [square root of |U|]
 * 
 * Example:
 * Given a universe of [0, 15], |U| = 16, with n =
 * {(1,5),(2,38),(11,9),(13,100),(14,-1)}, the data structure
 * The map is made of summary and value array.
 * The value array will be as follows:
 * -1, 5, 38, -1, -1, -1, -1, -1, -1, -1, -1, 9, -1, 100, 200, -1, -1
 * The summary array will be as follows:
 * 1,0,1,1
 * the first 1 tells you that there exists atleast one key in the range of (0-3)
 * the second 0 tells you that there exists does not exist any key in the range
 * of (4-7)
 * and so on
 * 
 * Use the summary array to efficiently implement minimum and maximum in
 * O(|U|^(0.5)) time
 * 
 */
class map_augmented_direct_address_table {

  // Initialize the value array to -1
  // Initialize the summary array to 0

  // if value[i] == -1, key i does not exist in the map
  // if value[i] != -1, key i exist in the map

  private int universe_size;
  private int[] summary;
  private int[] value;

  public map_augmented_direct_address_table(int us) {
    universe_size = us;
    value = new int[us];
    // TODO: inititlize the value array with all -1 (empty)
    for (int i = 0; i < us; i++)
      value[i] = -1;

    int summary_size = (int) Math.sqrt(universe_size);
    summary = new int[summary_size];
    // TODO: inititlize the summary array with all 0 (empty)
    for (int i = 0; i < summary_size; i++)
      summary[i] = 0;
  }

  // [20 Points]
  // return -1 if the key already exists, and you have to perform a replace
  // operation
  // return the key if the key is successfully inserted
  public int insert(int key, int value) {
    // TODO: Implement this
    return -1;
  }

  // [20 Points]
  // return value if the key already exists
  // return -1 if the key does not exist
  public int search(int key) {
    // TODO: Implement this
    return -1;
  }

  // [20 Points]
  // return the maximum key-value
  // return -1 if the hash able is empty
  public int maximum() {
    // TODO: Implement this
    return -1;
  }

  // [20 Points]
  // return the minimum key-value
  // return -1 if the hash able is empty
  public int minimum() {
    // TODO: Implement this
    return -1;
  }

  // [20 Points]
  // Return an array arr of size 2
  // arr[0] the key which has most number of occurence,
  // arr[1] the number of occurence of arr[0]
  public int[] return_key_with_maximum_repetition() {
    int[] arr = new int[2]; // combining both statements in one

    // You will have to traverse the data structure
    // TODO: Implement this
    return arr;
  }

}

public class AlgorithmsHW4 {
  public static void main(String[] args) {
    int key_count = 400000;
    int univers_size = 16777216;

    map_BST ht_set1 = new map_BST();

    map_augmented_direct_address_table map_adatm = new map_augmented_direct_address_table(16777216);

    /********************* CREATE THE KEYS ********************/
    // Create key_count keys (n), randomly
    int[] keys_set1 = new int[key_count];
    for (int k = 0; k < key_count; k++)
      keys_set1[k] = (int) (Math.random() * univers_size) % univers_size;

    /********************* INSERT ********************/
    // Insert the keys in the augmented direct address table
    // you are inserting key and the number of occurence of the key as the value
    long start_p1 = System.nanoTime();
    int insert_counter_ht = 0;
    for (int k = 0; k < key_count; k++) {
      int value = map_adatm.search(keys_set1[k]);
      if (value == -1)
        map_adatm.insert(keys_set1[k], 1);
      else
        map_adatm.insert(keys_set1[k], value + 1);
    }
    long end_p1 = System.nanoTime();

    // add insert code for BST based data structure
    for (int k = 0; k < key_count; k++) {
      int value = ht_set1.search(ht_set1.root, keys_set1[k]);
      if (value == -1)
        ht_set1.root = ht_set1.insert(ht_set1.root, keys_set1[k], 1);
      else
        ht_set1.root = ht_set1.insert(ht_set1.root, keys_set1[k], value + 1);
    }

    // add code to verify correctness of minimum, maximum functions
    Node max = ht_set1.maximum(ht_set1.root);
    int valid_max_key = -1;
    for (int i = 0; i < key_count; i++) {
      if (keys_set1[i] > valid_max_key)
        valid_max_key = keys_set1[i];
    }
    if (max != null && valid_max_key == max.key)
      System.out.println("The maximum is valid");
    else
      System.out
          .println("The maximum is invalid(" + Integer.toString(valid_max_key) + " ," + Integer.toString(max.key));

    Node min = ht_set1.minimum(ht_set1.root);
    int valid_min_key = univers_size;
    for (int i = 0; i < key_count; i++) {
      if (keys_set1[i] < valid_min_key)
        valid_min_key = keys_set1[i];
    }
    if (min != null && valid_min_key == min.key)
      System.out.println("The minimum is valid.");
    else
      System.out.println("The minimum is invalid.(" +
          Integer.toString(valid_min_key) + " ," + Integer.toString(min.key));

    // Call the function return_key_with_maximum_repetition to return the key

    int arr[] = ht_set1.return_key_with_maximum_repetition();
    System.out.println("repeats the most along with the number of repetition is:");
    System.out.println("Key is: " + Integer.toString(arr[0]));
    System.out.println("Value is: " + Integer.toString(arr[1]));

  }
}