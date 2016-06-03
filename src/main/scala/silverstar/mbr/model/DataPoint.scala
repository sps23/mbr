package silverstar.mbr.model

/**
  * Data point.
  */
trait DataPoint {

  def toList(): List[Double]
}
