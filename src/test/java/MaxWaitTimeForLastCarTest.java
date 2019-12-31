import org.junit.Assert;
import org.junit.Test;


public class MaxWaitTimeForLastCarTest extends MaxWaitTimeForLastCar{

    @org.junit.Before
    public void setup(){
        MaxWaitTimeForLastCar MX = new MaxWaitTimeForLastCar();
    }

    @Test
    public void maxWaitTimePositiveCase() {
        int [] d = new int[]{2,8,4,3,2};
        Assert.assertEquals(maxWaitTimeForLastCar(d,7,11,3),8);
    }

    @Test
    public void maxWaitTimeNegativeCase1() {
        //when none of the pump has enough to fill the car
        int[] d = new int[]{5};
        Assert.assertEquals(maxWaitTimeForLastCar(d,4,0,3),-1);
    }

    @Test
    public void maxWaitTimeNegativeCase2() {
        //when all pumps with amount 0
        int [] d = new int[]{5,3,2};
        Assert.assertEquals(maxWaitTimeForLastCar(d,0,0,0),-1);
    }

    @Test
    public void maxWaitTimeNegativeCase3() {
        //when pumps can't fulfill requirements of all cars in line
        int [] d = new int[]{5,3,2};
        Assert.assertEquals(maxWaitTimeForLastCar(d,4,0,0),-1);
    }
}