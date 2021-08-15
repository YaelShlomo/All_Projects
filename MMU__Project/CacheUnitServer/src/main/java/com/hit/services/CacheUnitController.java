package com.hit.services;

import com.hit.dm.DataModel;

public class CacheUnitController <T> {
    private CacheUnitService<T> cacheUnitService;

    public CacheUnitController() {
        cacheUnitService = new CacheUnitService<T>();
    }

    /**
     *
     * @param dataModels is array of params with dataModel type that we want to update
     * @return
     * @throws Exception
     */
    public boolean update(DataModel<T>[] dataModels) throws Exception{
        return this.cacheUnitService.update(dataModels);
    }

    /**
     *
     * @param dataModels is array of params with dataModel type that we want to delete
     * @return true in case of success and false in case of failure
     */
    public boolean delete(DataModel<T>[] dataModels) {
        return this.cacheUnitService.delete(dataModels);
    }

    /**
     *
     * @param dataModels is array of params from dataModels type to look for in the cache unit
     * @return the params that found
     * @throws Exception if there is a problem
     */
    public DataModel<T>[] get(DataModel<T>[] dataModels) throws Exception {
        return this.cacheUnitService.get(dataModels);
    }

    /**
     * This is function that I odd that save the params from the cache befor shot down the server
     * @throws Exception if there is problem
     */
    public void shotDown() throws Exception {cacheUnitService.shotDown();}
    public String showStats() {return cacheUnitService.showStats();};
}
