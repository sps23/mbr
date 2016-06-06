package silverstar.mbr.service

/**
  * Normalization min max.
  */
class NormalizationMinMax extends Normalization {

  override def normalize(data: List[Double]): List[Double] = {
    val dataNormalized: List[Double] =
      if (data.isEmpty) {
        data
      } else {
        val min: Double = data.min
        val maxMinusMin: Double = data.max - min

        if (maxMinusMin == 0) {
          data.map(d => 1.0)
        } else {
          data.map(d => (d - min) / maxMinusMin)
        }
      }
    dataNormalized
  }
}

object NormalizationMinMax extends NormalizationMinMax
