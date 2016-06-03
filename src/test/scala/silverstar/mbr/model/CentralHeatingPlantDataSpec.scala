package silverstar.mbr.model

import org.scalatest.{FunSpec, Matchers}

/**
  * Tests for CentralHeatingPlantData.
  */
class CentralHeatingPlantDataSpec  extends FunSpec with Matchers {

  describe("CentralHeatingPlantData - should") {

    it("return difference") {
      val minuend: CentralHeatingPlantData = CentralHeatingPlantData(10,10,10)
      val subtrahend: CentralHeatingPlantData = CentralHeatingPlantData(2,5,7)
      val expectedDifference: CentralHeatingPlantData = CentralHeatingPlantData(8,5,3)

      minuend - subtrahend shouldBe expectedDifference
    }

    it("return List[Double]") {
      CentralHeatingPlantData(1,2,3).toList() shouldBe List(1.0, 2.0, 3.0)
    }
  }
}
