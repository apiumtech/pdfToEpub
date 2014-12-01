package com.apium.any.tree.generator

import com.apium.any.tree.{TreeNode, Document}

/**
 * Created by kevin on 11/29/14.
 */

trait GeneratorSource[T] {
  def source: T
}

trait Generator[GEN <: GeneratorSource[_]] {
  def generate(source: GEN): TreeNode
}

trait DocumentGenerator[GEN <: GeneratorSource[_]] extends Generator[GEN] {
  def generate(source: GEN): Document
}