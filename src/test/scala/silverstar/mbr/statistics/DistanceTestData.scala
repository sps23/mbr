package silverstar.mbr.statistics

import silverstar.mbr.model.CentralHeatingPlantData

/**
  * Test data for Distance.
  */
object DistanceTestData {


  val data1: List[Double] = List(1.0, 1.0, 1.0)
  val dataOnly2Points: List[Double] = List(1.0, 0.2)
  val data2: List[Double] = List(1.0, 1.0, 1.0, 1.0)
  val data3: List[Double] = List(1.0, 0.2, 1.0, 0.4)

  val chpData1: CentralHeatingPlantData = CentralHeatingPlantData(1, 1, 1)
  val chpData2: CentralHeatingPlantData = CentralHeatingPlantData(0.2, 1, 0.4)
  val chpData3: CentralHeatingPlantData = CentralHeatingPlantData(0, 0.8, 0.6)
}
