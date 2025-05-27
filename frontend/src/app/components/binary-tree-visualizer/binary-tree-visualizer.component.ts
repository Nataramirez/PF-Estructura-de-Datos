import { Component, ElementRef, ViewChild } from '@angular/core';
import { BinaryTreeService } from '../../services/binary-tree/binary-tree.service';
import { NodeBinaryTree } from '../../services/binary-tree/Node';
import { Book } from '../../models/book.model';
import * as d3 from 'd3';
import { GlobalStateService } from '../../services/global-state.service';
import { LibraryServicesService } from '../../services/library-services.service';

@Component({
  selector: 'app-binary-tree-visualizer',
  imports: [],
  templateUrl: './binary-tree-visualizer.component.html',
  styleUrl: './binary-tree-visualizer.component.css'
})

export class BinaryTreeVisualizerComponent {
  @ViewChild('treeContainer', { static: true }) treeContainer!: ElementRef;
  private books: Book[] = []

  constructor(
    private binaryTreeService: BinaryTreeService,
    private globalState: GlobalStateService,
    private libraryServicesService: LibraryServicesService,
  ) { }

  async ngOnInit() {
    this.books = await this.libraryServicesService.getAllBooks();
    this.binaryTreeService.deleteBinaryTree();
    this.books.forEach(book => {
      this.binaryTreeService.addNode(book.name);
    });
    const root = this.binaryTreeService.getRoot();
    if (root) {
      const treeData = this.buildD3Tree(root);
      this.drawTree(treeData);
    }
  }

  private buildD3Tree(node: NodeBinaryTree<string>): any {
    if (!node) return null;
    return {
      name: node.value.toString(),
      children: [
        node.left ? this.buildD3Tree(node.left) : null,
        node.right ? this.buildD3Tree(node.right) : null
      ].filter(child => child !== null)
    };
  }

  private drawTree(treeData: any): void {
    const element = this.treeContainer.nativeElement;
    const margin = { top: 20, right: 90, bottom: 30, left: 90 };
    const width = 1400 - margin.left - margin.right;
    const height = 1200 - margin.top - margin.bottom;

    const svg = d3
      .select(element)
      .append('svg')
      .attr('width', width + margin.left + margin.right)
      .attr('height', height + margin.top + margin.bottom)
      .attr('background', '#F5EEDD')
      .append('g')
      .attr('transform', 'translate(' + margin.left + ',' + margin.top + ')');

    const root = d3.hierarchy(treeData);
    const treeLayout = d3.tree().size([width, height]);
    const tree = treeLayout(root);

    // Links
    svg
      .selectAll('.link')
      .data(tree.links())
      .enter()
      .append('path')
      .attr('class', 'link')
      .attr('fill', 'none')
      .attr('stroke', '#ccc')
      .attr('stroke-width', 2)
      .attr('d', d3.linkVertical<d3.HierarchyPointLink<any>, d3.HierarchyPointNode<any>>()
        .x((d: any) => d.x)
        .y((d: any) => d.y)
      );

    // Nodes
    const node = svg
      .selectAll('.node')
      .data(tree.descendants())
      .enter()
      .append('g')
      .attr('class', 'node')
      .attr('transform', (d: any) => `translate(${d.x},${d.y})`);

    const rectWidth = 150;
    const rectHeight = 40;

    node
      .append('rect')
      .attr('x', -rectWidth / 2) // centrar horizontalmente (ancho / 2)
      .attr('y', -rectHeight / 2) // centrar verticalmente (alto / 2)
      .attr('width', rectWidth)
      .attr('height', rectHeight)
      .attr('rx', 10) // esquinas redondeadas opcionales
      .attr('fill', '#183F4E');

    node
      .append('text')
      .attr('dy', '.35em')
      .attr('text-anchor', 'middle')
      .style('fill', 'white')
      .style('font-size', '10px')
      .text((d: any) => d.data.name);
  }
}

