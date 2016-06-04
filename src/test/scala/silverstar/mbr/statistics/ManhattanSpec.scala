package silverstar.mbr.statistics

import org.scalatest.{FunSpec, Matchers}
import silverstar.mbr.statistics.DistanceTestData._

/**
  * Tests for Manhattan distance.
  */
class ManhattanSpec  extends FunSpec with Matchers {

  describe("Manhattan distance for List[Double] should") {

    it("return None if list lengths do not match") {
      Manhattan.distance(data1, dataOnly2Points) shouldBe None
    }

    it("return None for empty lists") {
      Manhattan.distance(List(), List()) shouldBe None
    }

    it("return 0 for identical lists (identity check)") {
      Manhattan.distance(data1, data1) shouldBe Some(0.0)
    }

    it("return result") {
      Manhattan.distance(data2, data3) shouldBe Some(1.4 / 4.0)
    }
  }

  describe("Manhattan distance for CentralHeatingPlantData should") {

    it("return 0 for identical data (identity check)") {
      Manhattan.distance(chpData1, chpData1) shouldBe Some(0.0)
    }

    it("return same result if we swap data (commutativity check)") {
      Manhattan.distance(chpData1, chpData2) shouldBe Manhattan.distance(chpData2, chpData1)
    }

    it("return result conforming triangular inequality") {
      val distanceAB: Double = Manhattan.distance(chpData1, chpData2).getOrElse(0.0)
      val distanceACPlusCB: Double = Manhattan.distance(chpData1, chpData3).getOrElse(0.0) +
        Manhattan.distance(chpData3, chpData2).getOrElse(0.0)
      distanceAB <= distanceACPlusCB shouldBe true
    }

    it("return result") {
      Manhattan.distance(chpData1, chpData2) shouldBe Some(1.4 / 3.0)
    }
  }
}
