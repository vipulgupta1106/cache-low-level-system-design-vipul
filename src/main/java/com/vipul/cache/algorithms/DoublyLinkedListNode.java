package com.vipul.cache.algorithms;

import lombok.Getter;

@Getter
public class DoublyLinkedListNode<E> {
	public DoublyLinkedListNode<E> prev;
	public DoublyLinkedListNode<E> next;
	E element;

	public DoublyLinkedListNode(E element){
		this.prev = null;
		this.next = null;
		this.element = element;
	}
}
