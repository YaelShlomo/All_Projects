package com.hit.dao;
/*This is an interface that simulates organizing a computer's hard disk
* @param <ID> is the of page
* @param T is page content */
public interface IDao<ID extends java.io.Serializable,T> {
    void save(T entity) throws Exception;
    /*A function that saves a new page to the hard disk
    * @param entity is the page that we want to save
    * @ throws Exception in case of error in reading or writing to file */
    void delete(T entity) throws  Exception;
    /*A function that deletes a new page to the hard disk
     * @param entity is the page that we want to delete
     * @throws Exception in case of error in reading or writing to file */
    T find(ID id) throws Exception;
    /*
    * A function that receives a key to the page, and searches for it on the hard disk.
    *@returns the page to which the key points, if the key founded.
    *Otherwise the function returns Nell
    * @param id is the id of the page that we want to find
    * @throws Exception in case of error in reading from file
    */
}

