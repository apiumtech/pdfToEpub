package com.apium.any.tree

/**
 * Created by kevin on 11/30/14.
 */

trait PageChildNode extends TreeNode

case class Page(override val children: Seq[PageChildNode]) extends ChapterChildNode
