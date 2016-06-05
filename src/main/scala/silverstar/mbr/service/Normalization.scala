package silverstar.mbr.service

/**
  * Normalization service.
  */
trait Normalization {

  def normalize(data: List[Double]): List[Double]
}
