import { Component, OnInit } from '@angular/core';
import { Libro } from '../../model/libro.model';
import { Autor } from '../../model/autor.model';
import { Categoria } from '../../model/categoria.model';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-libro',
  standalone: false,
  templateUrl: './libro.html',
  styleUrl: './libro.css',
})
export class LibroComponent implements OnInit {

   libros: Libro[] = [];
   autores : Autor[] = [];
   categorias : Categoria[] = [];
   libro : Libro = {} as Libro;
   editar : boolean = false;
   idEditar : number | null = null;
   dataSource!: MatTableDataSource<Libro>;
   seleccionarArchivo!: File;
   imagenPrevia: string ="";




  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

}
