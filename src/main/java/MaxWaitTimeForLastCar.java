import java.util.LinkedList;
import java.util.Queue;

public class MaxWaitTimeForLastCar {
    /*
    QUE:
    There are 3 fueling stations with fixed supply of fuel defined by integers s1, s2 and s3 respectively.
    The fueling station takes 1 second to dispense 1 gallon of fuel.
    There is a queue of cars waiting for fuel. Each car in the queue has a predefined demand for the fuel defined by an int[] d.
    Only the car at the head of the queue should be served by one of the fueling stations.
    Cars can never break the queue/line under any circumstance.
    If the demand of a car can be fulfilled by more than one fueling station, then the car should go to the fueling station with the least index. For example, if s2 and s3 are free, then the car should go to s2.
    Assume that it takes negligible amount of time for the car to move from the head of the queue to the pump.
    Find how long the last car has to wait to get fueled.
    Essentially implement the Java method below:


    Please send the unit tests along with the solution.
    Think of corner cases and comment the code appropriately.
    Think about large input values and how your implementation scales.
    What other alternative have you considered and why have you selected a specific implementation?
    */

    int maxWaitTimeForLastCar(int[] d, int p1, int p2, int p3) {
        // Implementation goes here

        // Wait counter
        int wait = 0;

        //maintain pump status : true means available
        Boolean[] pumpStatus = new Boolean[3];

        //initialize with true: initially all pumps are available
        for (int i = 0; i < pumpStatus.length; i++) {
            pumpStatus[i] = true;
        }

        //maintain fuel time window at each pump
        int[] fuelfillingTimeOfCar = new int[3];

        //create queue of cars
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < d.length; i++) {
            queue.add(d[i]);
        }

        //process until all cars in line are processed or return -1
        while (!queue.isEmpty()) {

            int car = queue.peek().intValue();

            //Base case: if fuel demand of car can't be fulfilled by any pump then return -1
            if (car > p1 && car > p2 && car > p3) {
                return -1;
            }

            //check if pump is available and pump has enough fuel to fulfill car's demand
            if (pumpStatus[0] == true && car <= p1) {
                //if true then subtract car fuel amount from total pump capacity and set pump is busy
                p1 -= car;
                fuelfillingTimeOfCar[0] = car;
                pumpStatus[0] = false;
                queue.remove();
            } else if (pumpStatus[1] == true && car <= p2) {
                p2 -= car;
                fuelfillingTimeOfCar[1] = car;
                pumpStatus[1] = false;
                queue.remove();
            } else if (pumpStatus[2] == true && car <= p3) {
                p3 -= car;
                fuelfillingTimeOfCar[2] = car;
                pumpStatus[2] = false;
                queue.remove();
            }
            //if any of the pump can't fulfill car's fuel requirement then car has to wait, so find min waiting time and assign it to wait
            else {

                int min = Integer.MAX_VALUE;
                for (int j = 0; j < fuelfillingTimeOfCar.length; j++) {
                    if (fuelfillingTimeOfCar[j] > 0 && fuelfillingTimeOfCar[j] < min) {
                        min = fuelfillingTimeOfCar[j];
                    }
                }
                wait += min;

                //to manage wait time window, once find min wait time then decrease it from all pump fuel filling time for next iteration
                for (int j = 0; j < fuelfillingTimeOfCar.length; j++) {
                    if (fuelfillingTimeOfCar[j] > 0) {
                        fuelfillingTimeOfCar[j] -= min;
                    }
                    //if pump is available again, change it's status to true
                    if (fuelfillingTimeOfCar[j] == 0) {
                        pumpStatus[j] = true;
                    }
                }
            }

        }
        return wait;
    }
    /*

    Time Complexity: worst case : O(no of cars * no of pumps), Best case: O(no of cars)
    Que: Please send the unit tests along with the solution.
    Ans: Unit tests are attached for positive and negative test cases.

    Que: Think of corner cases and comment the code appropriately.
    And:
        -Comments added into code
        -Corner cases: 1. when none of the pump has enough to fill the car
                       2. when all pumps with amount 0
                       3. when pumps can't fulfill requirements of all cars in queue

    Que: Think about large input values and how your implementation scales.
    Ans: We can use priority queue for pumps fueling time array to get min waiting time for each car. this way wee can make it more faster for larger inputs.

    Que: What other alternative have you considered and why have you selected a specific implementation?
    Ans: First I started with array instead of queue. But when there is car has to wait and have to process same element twice, at that time it is tricky to maintain it with array solution.
         So I used queue because of FIFO technique, as long as queue head element is not processed it will not go to second element.
         That's why I selected this implementation.
    */
}
