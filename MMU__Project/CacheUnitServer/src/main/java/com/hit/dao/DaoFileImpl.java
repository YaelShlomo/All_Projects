package com.hit.dao;

import com.hit.dm.DataModel;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/*Implementation to the IDao interface
* The class deals with pages whose keys are of the long type and the contents of the type DataModel
* @T is the type of the DataModel*/
public class DaoFileImpl<T> implements IDao<Long,DataModel<T>> {
    private String filePath;//The file path we are reading / writing to
    private int capacity; //Capacity of the map
    private Map<Long, DataModel<T>> map; //Stores the contents of the current or recent file
    private ObjectOutputStream objStream;

    public DaoFileImpl(String filePath) {
        //The writing is done to an absolute location in the project - you can only enter the file name.
        this.filePath = new File("src/main/resources/"+filePath).getAbsolutePath();
        map = new HashMap<Long, DataModel<T>>();
    }
    public DaoFileImpl(String filePath, int capacity) {
        //The writing is done to an absolute location in the project - you can only enter the file name.
        this.filePath = new File("src/main/resources/"+filePath).getAbsolutePath();
        this.capacity = capacity;
        this.map = new HashMap<Long, DataModel<T>>(capacity);
    }

    /*A function that reads from the hard disk into the help map*/
    private void read() throws Exception{
        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(filePath)))
        {
            this.map = (Map<Long, DataModel<T>>) os.readObject();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /*A function that reads from the help map to hard disk recognition, and updates it*/
    private void updateFile() throws Exception{
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filePath)))
        {
            os.writeObject(this.map);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }




    @Override
    public DataModel<T> find(Long id) throws Exception {
        read();
        return this.map.get(id);
    }

    @Override
    public void save(DataModel<T> entity) throws Exception {
        read();
        if(entity == null)return;
        DataModel<T> entityFromROM = find(entity.getDataModelId());
        if (entity != null) {
            if (entity != entityFromROM) {
                this.map.put(entity.getDataModelId(), entity);
            }
            updateFile();
        }
    }

    @Override
    public void delete(DataModel<T> entity) throws Exception {
        read();
        this.map.remove(entity.getDataModelId());
        updateFile();
    }

    public void clearHradDisk() throws Exception {
        this.map.clear();
        updateFile();
    }
}
