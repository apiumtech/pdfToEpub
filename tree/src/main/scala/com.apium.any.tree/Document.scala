package com.apium.any.tree

/**
 * Created by kevin on 11/28/14.
 */
case class Document(override val children: Seq[Chapter]) extends TreeNode with EnsuredChildren