package silverstar.mbr.statistics

import silverstar.mbr.model.DataPoint

/**
  * Distance calculation.
  */
trait Distance {

  def distance(x: List[Double], y: List[Double]): Option[Double]
  def distance(x: DataPoint, y: DataPoint): Option[Double]
}