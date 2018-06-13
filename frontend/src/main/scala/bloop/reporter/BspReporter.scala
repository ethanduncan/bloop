package bloop.reporter

import bloop.Project
import bloop.io.AbsolutePath
import bloop.logging.BspLogger
import xsbti.Position

import scala.collection.mutable

final class BspReporter(
    val project: Project,
    override val logger: BspLogger,
    override val cwd: AbsolutePath,
    sourcePositionMapper: Position => Position,
    override val config: ReporterConfig,
    override val _problems: mutable.Buffer[Problem] = mutable.ArrayBuffer.empty
) extends Reporter(logger, cwd, sourcePositionMapper, config, _problems) {
  override protected def logFull(problem: Problem): Unit = logger.diagnostic(problem)
  override def printSummary(): Unit = {
    logger.publishBspReport(project.bspUri, allProblems)
  }
}
