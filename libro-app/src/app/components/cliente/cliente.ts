import { Component, ElementRef, OnInit, viewChild, ViewChild } from '@angular/core';
import { Cliente } from '../../model/cliente.model';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ClienteService } from '../../services/cliente';
import Swal from 'sweetalert2';
import { Title } from '@angular/platform-browser';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-cliente',
  standalone: false,
  templateUrl: './cliente.html',
  styleUrl: './cliente.css',
})
export class ClienteComponent implements OnInit {
  
@ViewChild('formularioCliente') formularioCliente!: ElementRef;
@ViewChild(MatPaginator)paginator!: MatPaginator;
@ViewChild(MatSort) sort!: MatSort;

clientes: Cliente[] = [];
cliente: Cliente = { } as Cliente;
editar: boolean = false;
idEditar: number | null = null;

dataSource!: MatTableDataSource<Cliente>;
mostrarColumnas: String[] = ['idCliente', 'cedula', 'nombre', 'apellido','direccion', 'telefono', 'correo', 'acciones'];

  
constructor(private clienteService: ClienteService) { }


  ngOnInit(): void {
    this.findAll();
  }

  findAll() : void{
    this.clienteService.findAll().subscribe(data => { 

    //this.clientes = data;
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    });
  }

  save(): void{
    this.clienteService.save(this.cliente).subscribe(() => { 
      this.cliente = { } as Cliente;
      this.findAll();
    });
  }

  update(): void{
    if(this.idEditar !== null){
      this.clienteService.update(this.idEditar, this.cliente).subscribe(() => { 
        this.cliente = { } as Cliente;
        this.editar = false;
        this.idEditar = null;
        this.findAll();
      });
    }
  }


  delete(): void{
    //this.clienteService.delete(this.cliente.idCliente).subscribe(() => {});

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
      if(result.isConfirmed){
        this.clienteService.delete(this.cliente.idCliente).subscribe( () => {
          this.findAll();
          this.cliente = { } as Cliente;
          Swal.fire('Eliminado', 'El cliente ha sido eliminado', 'success');
         });
      } else {
        this.cliente = { } as Cliente;
      }
     });
  }

  //interaccion con la pagina web

  editarCliente(cliente: Cliente): void {
    this.cliente = { ...cliente };
    this.idEditar = cliente.idCliente;
    this.editar = true;

    setTimeout(() => {
      this.formularioCliente.nativeElement.scrollIntoView8({ behavior: 'smooh', block: 'start' });
    }, 100);
  }

  editarClienteCancelar(from: NgForm): void{
    this.cliente = { } as Cliente;
    this.idEditar =  null;
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

