package com.hit.services;

import com.hit.dao.*;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;
import com.mby.algorithm.IAlgoCache;
import com.mby.algorithm.LRUAlgoCacheImpl;

public class CacheUnitService<T> {

    private IDao<Long, DataModel<T>> DB;
    private CacheUnit<T> cacheUnit;
    private IAlgoCache<Long, DataModel<T>> algo;
    private Integer cacheUnitCapacity;

    private static final String algorithm = "LRU";
    private static final Integer capacity = 4;
    private Integer swaps = 0;
    private Integer requests = 0;
    private Integer dataModels = 0;

    public CacheUnitService() {
        this.cacheUnitCapacity = 10;
        this.algo = new LRUAlgoCacheImpl<Long, DataModel<T>>(this.cacheUnitCapacity);
        this.DB = new DaoFileImpl<T>("dataresource.txt");
        this.cacheUnit = new CacheUnit<T>(this.algo);
    }

    /**
     *
     * @param dataModels is dataNodels that we want to save in the cache, is the cache is full save in the DB
     * @return true in case of success
     * @throws Exception if there is problem
     */
    public boolean update(DataModel<T>[] dataModels) throws Exception {
        DataModel<T>[] arr = this.cacheUnit.putDataModels(dataModels);
        for (int i = 0; i < dataModels.length; i++) {
            this.dataModels++;
            if (arr[i] != null) {
                this.DB.save(arr[i]);
                this.swaps++;
            }
        }
        requests++;
        return true;
    }

    /**
     *
     * @param dataModels is dataNodels that we want to delete from the cache and the DB
     * @return true in case of success, and false in case of fail
     */
    public boolean delete(DataModel<T>[] dataModels) {
        try {
            Long idsToDelete[] = new Long[dataModels.length];
            for (int i = 0; i < dataModels.length; i++) {
                idsToDelete[i] = dataModels[i].getDataModelId();
            }
            for (int i = 0; i < dataModels.length; i++) {
                if (this.DB.find(idsToDelete[i]) != null) {
                    this.DB.delete(dataModels[i]);
                   this.dataModels++;
                }
            }
            requests++;
            this.cacheUnit.removeDataModels(idsToDelete);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }

    /**
     *
     * @param dataModels is dataModels that we want to get from the cache and the DB
     * @return the dataModels that found
     * @throws Exception if there is a problem
     */
    public DataModel<T>[] get(DataModel<T>[] dataModels) throws Exception {
        requests++;
        Long idsToGet[] = new Long[dataModels.length];
        int len = dataModels.length;
        DataModel<T>[] dataModelsArr = new DataModel[len];
        for (int i = 0; i < dataModels.length; i++) {
            idsToGet[i] = dataModels[i].getDataModelId();
            this.dataModels++;
        }
        for (int i = 0; i < dataModels.length; i++) {
            if (dataModels[i] != null) {
                dataModelsArr[i] = (DataModel<T>) this.DB.find(dataModels[i].getDataModelId());
            }
        }
        return dataModelsArr;

    }

    /**
     *
     * @throws Exception if there is problem
     */
    public void shotDown() throws Exception {
        if (cacheUnit != null) {
            DataModel<T>[] map = cacheUnit.getAllDataModels();
            for (DataModel<T> element : map) {
                if (element != null) {
                    System.out.println(element.toString());
                    this.DB.save(element);
                }
            }
        }
        cacheUnit = null;

    }

    public String showStats() {
        String stats = algorithm + " " + capacity + " " + swaps + " " + requests + " " + dataModels;
        return stats;
    }
}
