import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-todos',
  templateUrl: './todos.component.html',
  styleUrls: ['./todos.component.css']
})
export class TodosComponent implements OnInit {
  public todos = [];
  public todo = '';

  constructor() { }

  ngOnInit(): void {
  }

  saveTodo() {
    const todoObject = {
      id: this.todos.length + 1,
      name: this.todo,
    };

    this.todos.push(todoObject);
    console.log(this.todos);
  }

  // countDown(todo) {
  //
  // }

  removeTodo(index) {
    this.todos.splice(index, 1);

  }


}
