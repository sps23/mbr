package silverstar.mbr.statistics

import silverstar.mbr.model.DataPoint

case class Euclidean() extends Distance {

  override def distance(x: DataPoint, y: DataPoint): Option[Double] =
    distance(x.toList(), y.toList())

  override def distance(x: List[Double], y: List[Double]): Option[Double] = {
    if (x.length == y.length) {
      if (x.isEmpty) None
      else {
        val sumOfDiffToSecondPower: Double = (x, y).zipped.foldLeft(0d)(op = (sum, tuple) =>
          sum + Math.pow(tuple._1 - tuple._2, 2))
        val distance: Double = Math.sqrt(sumOfDiffToSecondPower) / x.length.toDouble
        Some(distance)
      }
    }
    else None
  }
}

object Euclidean extends Euclidean
