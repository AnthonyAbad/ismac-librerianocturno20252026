import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Autor } from '../../model/autor.model';
import { MatTableDataSource } from '@angular/material/table';
import { AutorService } from '../../services/autor';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-autor',
  standalone: false,
  templateUrl: './autor.html',
  styleUrl: './autor.css',
})
export class AutorComponent implements OnInit {

@ViewChild('formularioAutor') formularioAutor!: ElementRef;
@ViewChild(MatPaginator)paginator!: MatPaginator;
@ViewChild(MatSort) sort!: MatSort;

autores: Autor[] = [];
autor: Autor = { } as Autor;
editar: boolean = false;
idEditar: number | null = null;

dataSource!: MatTableDataSource<Autor>;
mostrarColumnas: String[] = ['idAutor',  'nombre', 'apellido', 'pais', 'direccion', 'telefono', 'correo', 'acciones'];

 constructor(private autorService: AutorService) { }

 ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.autorService.findAll().subscribe(data => {

      //this.autores = data;
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  save(): void {
      this.autorService.save(this.autor).subscribe(() => {
        this.autor = {} as Autor;
        this.findAll();
      });
    }


     update(): void {
        if (this.idEditar !== null) {
          this.autorService.update(this.idEditar, this.autor).subscribe(() => {
            this.autor = {} as Autor;
            this.editar = false;
            this.idEditar = null;
            this.findAll();
          });
        }
      }


      delete(): void {
          //this.autorService.delete(this.cliente.idCliente).subscribe(() => {});
      
          Swal.fire({
            title: '¿Desea eliminar el dato?',
            text: 'Esta acción no se puede deshacer',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Si, eliminar',
            cancelButtonText: 'Cancelar',
            confirmButtonColor: '#d33',
            cancelButtonColor: '#3085d6'
          }).then((result) => {
            if (result.isConfirmed) {
              this.autorService.delete(this.autor.idAutor).subscribe(() => {
                this.findAll();
                this.autor = {} as Autor;
                Swal.fire('Eliminado', 'La categoria ha sido eliminada', 'success');
              });
            } else {
              this.autor = {} as Autor;
            }
          });
        }


         //interaccion con la pagina web

  editarAutor(autor: Autor): void {
    this.autor = { ...autor };
    this.idEditar = autor.idAutor;
    this.editar = true;

    setTimeout(() => {
      this.formularioAutor.nativeElement.scrollIntoView8({ behavior: 'smooh', block: 'start' });
    }, 100);
  }

  editarAutorCancelar(from: NgForm): void {
    this.autor = {} as Autor;
    this.idEditar = null;
    this.editar = false;
    from.resetForm;
  }


  guardar(from: NgForm): void{
    if(this.editar && this.idEditar !==  null){
      this.update();
      from.resetForm();
    }else{
      this.save();
      from.resetForm();
    }
  }


  filtro(event: Event){
    const filtro1 =  (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro1.trim().toLocaleLowerCase();
  }




}
