package com.apium.any.tree

/**
 * Created by kevin on 11/28/14.
 */

trait ChapterChildNode extends TreeNode

case class Chapter(title: String, override val children: Seq[ChapterChildNode]) extends TreeNode with EnsuredChildren
