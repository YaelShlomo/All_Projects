//import com.hit.dm.DataModel;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;
import com.mby.algorithm.LRUAlgoCacheImpl;
import com.mby.algorithm.MFUAlgoCacheImpl;
import org.junit.Assert;
import org.junit.Test;
import java.lang.Long;
public class CacheUnitTest{

    @Test
    public void cacheTest() {
       MFUAlgoCacheImpl<Long,DataModel<Long>> mfu=new MFUAlgoCacheImpl<>(3);
        CacheUnit<Long> cache=new CacheUnit<>(mfu);

        DataModel<Long>[] arrDataModel1=new DataModel[3];
        DataModel<Long> [] arrDataModel2;

        DataModel<Long> dataModel1=new DataModel<>(1L, 1L);
        DataModel<Long> dataModel2=new DataModel<>(2L, 2L);
        DataModel<Long> dataModel3=new DataModel<>(3L, 3L);
        DataModel<Long> dataModel4=new DataModel<>(4L, 4L);

        arrDataModel1[0]=dataModel1;
        arrDataModel1[1]=dataModel2;
        arrDataModel1[2]=dataModel3;

        arrDataModel2=cache.putDataModels(arrDataModel1);

        for (DataModel<Long> LongDataModel : arrDataModel2) {
            Assert.assertNull(LongDataModel);
        }

        Long[] longArray=new Long[3];

        longArray[0]=dataModel1.getDataModelId();
        longArray[1]=dataModel2.getDataModelId();
        longArray[2]=dataModel3.getDataModelId();

        arrDataModel2=cache.getDataModels(longArray);

        for(int i=0;i<arrDataModel2.length;i++){
            Assert.assertEquals(arrDataModel1[i],arrDataModel2[i]);
        }

        longArray[1]=dataModel1.getDataModelId();

        arrDataModel2=cache.getDataModels(longArray);

        for(int i=0;i<arrDataModel2.length;i++){
            if(i==1){
                Assert.assertEquals(arrDataModel1[0],arrDataModel2[i]);
            }
            else {
                Assert.assertEquals(arrDataModel1[i], arrDataModel2[i]);
            }
        }

        DataModel<Long> [] arrDataModel3=new DataModel[1];

        arrDataModel3[0]=dataModel4;
        arrDataModel3=cache.putDataModels(arrDataModel3);

        Assert.assertEquals(dataModel1,arrDataModel3[0]);

        Long[] longArray2=new Long[2];

        longArray2[0]= 2L;
        longArray2[1]= 3L;

        cache.removeDataModels(longArray2);

        arrDataModel1[0]=dataModel4;

        longArray[0]=dataModel4.getDataModelId();

        arrDataModel2=cache.getDataModels(longArray);

        for(int i=0;i<arrDataModel2.length;i++){
            if(i==0){
                Assert.assertEquals(arrDataModel1[0],arrDataModel2[i]);
            }
            else{
                Assert.assertNull(arrDataModel2[i]);
            }
        }
    }

}