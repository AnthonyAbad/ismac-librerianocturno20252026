import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Libro } from '../model/libro.model';

@Injectable({
  providedIn: 'root',
})
export class LibroService {
  
    private baseUrl = "http://localhost:8080/api/libro"

  constructor(private http: HttpClient){  } 

  findAll(): Observable<Libro[]>{
    return this.http.get<Libro[]>(this.baseUrl);
  }

  findOne(id: number): Observable<Libro>{
    return this.http.get<Libro>(`${this.baseUrl}/${id}`);
  }

  save(Libro: Libro): Observable<Libro>{
    return this.http.post<Libro>(this.baseUrl, Libro);
  }

  update(id: number, Libro: Libro): Observable<Libro>{
    return this.http.put<Libro>(`${this.baseUrl}/${id}`, Libro);
  }  

  delete(id: number): Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
