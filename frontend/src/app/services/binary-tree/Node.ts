import { v4 as uuidv4 } from 'uuid';

export class NodeBinaryTree<T> {
  value: T;
  id: string;
  left: NodeBinaryTree<T> | null = null;
  right: NodeBinaryTree<T> | null = null;

  constructor(value: T) {
    this.value = value;
    this.id = uuidv4()
  }
}
