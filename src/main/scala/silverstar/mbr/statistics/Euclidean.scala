package silverstar.mbr.statistics

import silverstar.mbr.model.DataPoint

case class Euclidean() extends Distance {

  override def distance(x: List[Double], y: List[Double]): Option[Double] = {
    if (x.length == y.length) {
      if (x.isEmpty) None
      else Some(Math.sqrt((x, y).zipped.foldLeft(0d)((sum, tuple) => sum + Math.pow(tuple._1 - tuple._2, 2))) / x.length.toDouble)
    }
    else None
  }

  override def distance(x: DataPoint, y: DataPoint): Option[Double] =
    distance(x.toList(), y.toList())
}

object Euclidean extends Euclidean
