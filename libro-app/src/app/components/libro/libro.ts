import { Component, ElementRef, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Libro } from '../../model/libro.model';
import { Autor } from '../../model/autor.model';
import { Categoria } from '../../model/categoria.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { AutorService } from '../../services/autor';
import { CategoriaService } from '../../services/categoria';
import { MatDialog } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import { LibroService } from '../../services/libro';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';
import { from } from 'rxjs';

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
   libroSeleccionado : Libro  | null= null;


 mostrarColumnas: string[]= ['idLibro','titulo','editorial','numPaginas','edicion','idioma','fechaPublicacion','descripcion'
                              ,'tipoPasta','isbn','numEjemplares','portada','presentacion','precio','autor','categoria','acciones'  ];

  @ViewChild('formularioLibros')formularioLibro !: ElementRef;
  //@ViewChild('formLibro') formularioLibro!: NgForm;
  @ViewChild  (MatPaginator)paginator !: MatPaginator;
  @ViewChild (MatSort)sort !:MatSort;
  @ViewChild('modalLibro')modalLibro!: TemplateRef<any>;
  @ViewChild ('modalAutor')modalAutor !: TemplateRef<any>;
   @ViewChild ('modalCategoria')modalCategoria !: TemplateRef<any>;
      @ViewChild ('modalDetalles')modalDetalles !: TemplateRef<any>;
  constructor(
    private libroService : LibroService,
    private autorService : AutorService,
    private categoriaService : CategoriaService,
   
    private dialog : MatDialog,
    private http: HttpClient

  ){}

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void{
      this.libroService.findAll().subscribe(data=>{
        this.dataSource= new MatTableDataSource(data);
        this.dataSource.paginator= this.paginator;
        this.dataSource.sort= this.sort;
      });
    } 

    cargarLibros(): void{
      this.libroService.findAll().subscribe(data=>{this.libros=data;});
    }

     save (): void {
      this.libroService.save(this.libro).subscribe(()=>{
        this.libro={ }as Libro;
        this.findAll();
      });
    }

    update(): void{
      if(this.idEditar!==null){
        this.libroService.update(this.idEditar, this.libro).subscribe(()=>{
          this.libro= {} as Libro;
          this.editar=false;
          this.idEditar=null;
          this.findAll();
        });
      }
    }
     delete(): void {
      Swal.fire({
        title:'Desea eliminar el producto',
        text: 'Esta accion no se puede deshacer',
        icon: 'warning',
        showCancelButton:true,
        confirmButtonText: 'Si, eliminar',
        cancelButtonText:'Cancelar',
        confirmButtonColor:'#d33',
        cancelButtonColor:'#3085d6'
      }).then((result)=>{
        if(result.isConfirmed){
          this.libroService.delete(this.libro.idLibro).subscribe(()=>{
            this.findAll();
            this.libro={} as Libro;
            Swal.fire('Eliminado','El registro ha sido eliminado','success');
          });
        }else{
          this.libro={} as Libro;
        }
      });
    }

    //interaccion en la pagina
    editarLibro(libro : Libro): void{
      this.libro= {...libro};
      this.idEditar = libro.idLibro;
      this.editar= true;
      setTimeout(() => {
        this.formularioLibro.nativeElement.scrollIntoView({behavior : 'smooth', block: 'start'});
      }, 100);
    }

    editarLibroCancelar(form: NgForm):void{
      this.libro={ } as Libro;
      this.idEditar= null;
      this.editar = false;
      form.resetForm();

    }

    guardarLibro(): void{
      if(this.editar && this.idEditar !== null){
        this.update();

      }else {
        this.save();
      }
      this.dialog.closeAll();

    }


    filtroLibro(event : Event) : void{
      const filtro = (event.target as HTMLInputElement).value;
      this.dataSource.filter = filtro.trim().toLocaleLowerCase();

    }

    nombreCompletoAutor(autor : Autor): string{
      return `${autor.nombre} ${autor.apellido}`;
    }

    abrirModal(libro?: Libro): void{
      if(libro){
        this.libro= {...libro};
        this.editar = true;
        this.idEditar = libro.idLibro;
      }else {
        this.libro ={ } as Libro;
        this.editar= false;
        this.idEditar = null;
      }

      this.dialog.open(this.modalLibro,{ 
        width: '800px',
        disableClose : true
      });

    }

    compareAutores(a1: Autor, a2: Autor) :boolean{
      return a1 && a2 ? a1.idAutor === a2.idAutor: a1 === a2;
    }


    compareCategorias(c1 : Categoria, c2 : Categoria): boolean{
      return c1 && c2 ? c1.idCategoria === c2.idCategoria : c1 === c2;

    }

    onFileSelected(event: any){
      this.seleccionarArchivo = event.target.files[0];
    }

    subirImagen(): void{
      const formData = new FormData();
      formData.append("file", this.seleccionarArchivo);

      if(this.libro.portada){
        formData.append("oldImage", this.libro.portada);

      }

      this.http.post<{ruta: string }>('http://localhost:8080/api/upload-portada', formData).subscribe(res => {
        this.libro.portada = res.ruta;
        this.imagenPrevia = res.ruta;
       });
    }

    abrirModalDetalles(libro : Libro): void{
      this.libroSeleccionado = libro;
      this.dialog.open(this.modalDetalles, {
width : '500px'
      });
    }

    cerrarModal(): void {
      this.dialog.closeAll();
      this.libroSeleccionado = null;
    }

}
