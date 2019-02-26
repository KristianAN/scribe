package scribe.format

import scribe._
import scribe.output.LogOutput

trait Formatter {
  def format[M](record: LogRecord[M]): LogOutput
}

object Formatter {
  lazy val simple: Formatter = formatter"$message$mdc"
  lazy val classic: Formatter = formatter"$date [$threadNameAbbreviated] $level $position - $message$mdc"
  lazy val default: Formatter = formatter"$date $level $position - $message$mdc"
  lazy val enhanced: Formatter = formatter"$date ${string("[")}$levelColoredPaddedRight${string("]")} ${cyan(position)} - ${gray(message)}$mdc"
  lazy val strict: Formatter = formatter"$date [$threadNameAbbreviated] $levelPaddedRight $positionAbbreviated - $message$mdc"

  def fromBlocks(blocks: FormatBlock*): Formatter = new FormatBlocksFormatter(blocks.toList)
}