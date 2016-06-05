package silverstar.mbr.service

import org.scalatest.{FunSpec, Matchers}

/**
  * Tests for NormalizationMinMax.
  */
class NormalizationMinMaxSpec extends FunSpec with Matchers {

  describe("NormalizationMinMax normalize should") {

    it("return empty collection for empty collection") {
      val data: List[Double] = List()
      val dataNorm: List[Double] = NormalizationMinMax.normalize(data)
      dataNorm.length shouldBe 0
    }

    it("return normalized data for collection of same elements") {
      val data: List[Double] = (1 to 20).toList.map(v => 100.0)
      val dataNorm: List[Double] = NormalizationMinMax.normalize(data)
      dataNorm.max shouldBe 1.0
      dataNorm.min shouldBe 1.0
    }

    it("return normalized data for small data collection") {
      val data: List[Double] = List(100, 24, 99, 418, 3, 1.24, 0.99, 27)
      val dataNorm: List[Double] = NormalizationMinMax.normalize(data)
      dataNorm.max shouldBe 1.0
      dataNorm.min shouldBe 0.0
    }
  }

}
