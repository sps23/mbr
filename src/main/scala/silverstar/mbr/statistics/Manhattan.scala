package silverstar.mbr.statistics

import silverstar.mbr.model.DataPoint

/**
  * Manhattan distance.
  */
case class Manhattan() extends Distance {

  override def distance(x: List[Double], y: List[Double]): Option[Double] = {
    if (x.length == y.length) {
      if (x.isEmpty) None
      else Some((x, y).zipped.foldLeft(0d)((sum, tuple) => sum + Math.abs(tuple._1 - tuple._2)) / x.length.toDouble)
    }
    else None
  }

  override def distance(x: DataPoint, y: DataPoint): Option[Double] =
    distance(x.toList(), y.toList())
}

object Manhattan extends Manhattan
