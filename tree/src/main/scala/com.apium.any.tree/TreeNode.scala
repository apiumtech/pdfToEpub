package com.apium.any.tree

/**
 * Created by kevin on 11/28/14.
 */
trait TreeNode {
  final def name = this getClass() getName()

  def children: Seq[TreeNode]
}

trait NoChildren extends TreeNode {
  final def children = Seq()
}

trait EnsuredChildren extends TreeNode {
  require(children.nonEmpty)
}