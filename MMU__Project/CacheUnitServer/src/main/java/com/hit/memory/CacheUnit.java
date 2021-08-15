package com.hit.memory;

import com.hit.dm.DataModel;
import com.mby.algorithm.IAlgoCache;

import java.util.HashMap;
import java.util.Map;
import java.util.function.ToDoubleBiFunction;

/*Design of the Cache Unit Memory
* @param T is the type*/
public class CacheUnit<T> extends java.lang.Object{

    private IAlgoCache<Long, DataModel<T>> algo;//The algorithm that runs the cache

    public CacheUnit(com.mby.algorithm.IAlgoCache<java.lang.Long, DataModel<T>> algo) {
        this.algo = algo;//The function receives a cache management algorithm
    }
/*The function receives an array of keys, and returns the corresponding pages
* @param ids is the array od Ids of pages*/
    public DataModel<T>[] getDataModels(java.lang.Long[] ids) {
        int numOfDataToGet = ids.length;
        DataModel<T>[] newListOfDataModels = new DataModel[numOfDataToGet];
        DataModel<T> data;
        for (int i=0; i<numOfDataToGet; i++) {
            data = this.algo.getElement(ids[i]);
            newListOfDataModels[i] = data;
        }
        return newListOfDataModels;
    }
/*The function get array of objects from DataModel type to put in the cache
* @return an array of values that returned from the function putElement*/
    public DataModel<T>[] putDataModels(DataModel<T>[] datamodels) {
        int len = datamodels.length;
        DataModel<T>[] arrOfDataModels = new DataModel[len];
        for(int i=0; i<len; i++) {
            DataModel<T> data = this.algo.putElement(datamodels[i].getDataModelId(), datamodels[i]);
            if(data != null) {
                arrOfDataModels[i] = new DataModel<T>(data.getDataModelId(), data.getContent());
            }
        }
        return arrOfDataModels;
    }
/*
* The function get array of keys and remove the pages from the cache*/
    public void removeDataModels(java.lang.Long[] ids) {
        for (Long id:ids) {
            this.algo.removeElement(id);
        }
    }

    public DataModel<T>[] getAllDataModels(){
        Map<Long, DataModel<T>> map = new HashMap<>();
       // map=this.algo.getPages();
        // TODO: 27/05/2021  
        DataModel<T>[] data = new DataModel[map.size()];
        int counter=0;
        for (DataModel<T> element: map.values()) {
            data[counter]=element;
            counter++;
        }
        return data;
    }

}
