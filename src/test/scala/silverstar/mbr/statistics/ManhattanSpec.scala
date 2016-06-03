package silverstar.mbr.statistics

import org.scalatest.{FunSpec, Matchers}
import silverstar.mbr.model.CentralHeatingPlantData

/**
  * Tests for Manhattan distance.
  */
class ManhattanSpec  extends FunSpec with Matchers {

  describe("Manhattan distance for List[Double] should") {

    it("return None if list lengths do not match") {
      Manhattan.distance(List(1.0, 1.0, 1.0), List(1.0, 0.2)) shouldBe None
    }

    it("return None for empty lists") {
      Manhattan.distance(List(), List()) shouldBe None
    }

    it("return 0 for identical lists") {
      Manhattan.distance(List(1.0, 1.0, 1.0), List(1.0, 1.0, 1.0)) shouldBe Some(0.0)
    }

    it("return result") {
      Manhattan.distance(List(1.0, 1.0, 1.0, 1.0), List(1.0, 0.2, 1.0, 0.4)) shouldBe Some(1.4 / 4.0)
    }
  }

  describe("Manhattan distance for CentralHeatingPlantData should") {

    it("return 0 for identical data") {
      val data1: CentralHeatingPlantData = CentralHeatingPlantData(1,1,1)
      val data2: CentralHeatingPlantData = data1.copy()
      Manhattan.distance(data1, data2) shouldBe Some(0.0)
    }

    it("return result") {
      val data1: CentralHeatingPlantData = CentralHeatingPlantData(1,1,1)
      val data2: CentralHeatingPlantData = CentralHeatingPlantData(0.2,1,0.4)
      Manhattan.distance(data1, data2) shouldBe Some(1.4 / 3.0)
    }
  }
}
