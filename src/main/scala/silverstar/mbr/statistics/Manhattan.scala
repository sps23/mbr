package silverstar.mbr.statistics

import silverstar.mbr.model.DataPoint

/**
  * Manhattan distance.
  */
case class Manhattan() extends Distance {

  override def distance(x: DataPoint, y: DataPoint): Option[Double] =
    distance(x.toList(), y.toList())

  override def distance(x: List[Double], y: List[Double]): Option[Double] = {
    if (x.length == y.length) {
      if (x.isEmpty) {
        None
      } else {
        val sumOfDiffAbs: Double = (x, y).zipped.foldLeft(0d)((d, tuple) => {
          val diffAbs: Double = Math.abs(tuple._1 - tuple._2)
          val sum: Double = d + diffAbs
          sum
        })
        val distance: Double = sumOfDiffAbs / x.length.toDouble
        Some(distance)
      }
    } else {
      None
    }
  }
}

object Manhattan extends Manhattan
