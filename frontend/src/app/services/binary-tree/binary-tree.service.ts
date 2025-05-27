import { Injectable } from '@angular/core';
import { NodeBinaryTree } from './Node';
import { SearchBinaryTreeService } from './search-binary-tree.service';
import { Book } from '../../models/book.model';


@Injectable({
  providedIn: 'root'
})
export class BinaryTreeService {
  private root: NodeBinaryTree<string> | null;

  constructor(
    private searchBinaryTreeService: SearchBinaryTreeService<number>
  ) {
    this.root = null;
  }

  public getRoot(): NodeBinaryTree<string> | null {
    return this.root;
  }

  public isEmpty(): boolean {
    return this.root === null;
  }

  public addNode(value: string): void {
    const newNode = new NodeBinaryTree(value);
    if (this.root === null) {
      this.root = newNode;
    } else {
      this.insertNode(this.root, newNode);
    }
  }

  private insertNode(root: NodeBinaryTree<string>, newNode: NodeBinaryTree<string>): void {
    if (this.compareValues(root.value, newNode.value)) {
      if (root.right === null) {
        root.right = newNode;
      } else {
        this.insertNode(root.right, newNode);
      }
    } else {
      if (root.left === null) {
        root.left = newNode;
      } else {
        this.insertNode(root.left, newNode);
      }
    }
  }

  private compareValues(value1: string, value2: string): boolean {
    return value1 < value2 ? true : false;
  }

  public countLeaves(root: NodeBinaryTree<number> | null): number {
    if (root === null) {
      return 0;
    }
    if (root.left === null && root.right === null) {
      return 1;
    }
    return this.countLeaves(root.left) + this.countLeaves(root.right);
  }

  public weightBinaryTree(): number {
    //const binaryTree = this.searchBinaryTreeService.inOrder(this.root as NodeBinaryTree<number>);
    // return binaryTree.length;
    return 0;
  }

  public heightBinaryTree(root: NodeBinaryTree<number> | null): number {
    if (root === null) {
      return 0;
    }
    const leftHeight = this.heightBinaryTree(root.left);
    const rightHeight = this.heightBinaryTree(root.right);
    return Math.max(leftHeight, rightHeight) + 1;
  }

  public getNodeMax(): number {
    //const binaryTree = this.searchBinaryTreeService.inOrder(this.root as NodeBinaryTree<number>);
    //return binaryTree[binaryTree.length - 1];
    return 0;
  }

  public getNodeMin(): number {
    //const binaryTree = this.searchBinaryTreeService.inOrder(this.root as NodeBinaryTree<number>);
    //return binaryTree[0];
    return 0;
  }

  public deleteBinaryTree(): void {
    this.root = null;
  }
}
