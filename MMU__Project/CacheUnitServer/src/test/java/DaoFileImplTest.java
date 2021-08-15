
import com.hit.dao.DaoFileImpl;
import com.hit.dm.DataModel;
import org.junit.Assert;
import org.junit.Test;
//import org.testng.annotations.Test;

import java.lang.Long;
public class DaoFileImplTest {
    DaoFileImpl<Long> data=null;
    DataModel<Long> model2 = null;
    DataModel<Long> model1 = null;
    DataModel<Long> model3 = null;


    @Test
    public void daoTest() throws Exception {

        DaoFileImpl<String> data=new DaoFileImpl<>("datasource.txt");

        data.clearHradDisk();

        DataModel<String> modelb = new DataModel<>(2L, "bbb");
        DataModel<String> modela = new DataModel<>(1L, "aaa");
        DataModel<String> modelc = new DataModel<>(3L, "ccc");
        DataModel<String> modeld = new DataModel<>(4L, "ddd");

        Assert.assertEquals(null,data.find(2L));

        data.save(modela);

        Assert.assertEquals(null,data.find(2L));

        data.save(modelb);
        data.save(modelc);
        data.save(modeld);

        data.delete(modelc);

        Assert.assertEquals(modelb ,data.find(2L));
        Assert.assertEquals(null ,data.find(3L));

        data.save(modeld);
        
        Assert.assertEquals(modeld ,data.find(4L));
    }
}