import { Injectable } from '@angular/core';
import { NodeBinaryTree } from './Node';

@Injectable({
  providedIn: 'root'
})
export class SearchBinaryTreeService<T> {
  public inOrder(root: NodeBinaryTree<T>): T[] {
    const result: T[] = [];
    this.inOrderHelper(root, result);
    return result;
  }

  private inOrderHelper(node: NodeBinaryTree<T> | null, result: T[]): void {
    if (node !== null) {
      this.inOrderHelper(node.left, result);
      result.push(node.value);
      this.inOrderHelper(node.right, result);
    }
  }

  public preOrder(root: NodeBinaryTree<T>): T[] {
    const result: T[] = [];
    this.preOrderHelper(root, result);
    return result;
  }

  private preOrderHelper(node: NodeBinaryTree<T> | null, result: T[]): void {
    if (node !== null) {
      result.push(node.value);
      this.preOrderHelper(node.left, result);
      this.preOrderHelper(node.right, result);
    }
  }

  postOrder(root: NodeBinaryTree<T>): T[] {
    const result: T[] = [];
    this.postOrderHelper(root, result);
    return result;
  }

  private postOrderHelper(node: NodeBinaryTree<T> | null, result: T[]): void {
    if (node !== null) {
      this.postOrderHelper(node.left, result);
      this.postOrderHelper(node.right, result);
      result.push(node.value);
    }
  }
}
