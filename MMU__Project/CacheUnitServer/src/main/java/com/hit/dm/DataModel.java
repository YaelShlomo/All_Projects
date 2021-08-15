package com.hit.dm;
import java.util.Objects;
import java.lang.Long;
/*Realization of a page in memory
* @paran T is the type of the file content
* */
public class DataModel<T> extends java.lang.Object implements java.io.Serializable {
    private java.lang.Long id;//the key of the page
    private  T content;//the content of the page

   public DataModel(java.lang.Long id, T content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "id=" + id +
                ", content=" + content +
                '}';
    }

    public Long getDataModelId() {
        return this.id;
    }

    public T getContent() {
        return this.content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public void setDataModelId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataModel<T> dataModel = (DataModel<T>) o;
        return Objects.equals(id, dataModel.id) && Objects.equals(content, dataModel.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }

}