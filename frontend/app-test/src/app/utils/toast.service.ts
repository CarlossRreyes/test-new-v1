import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(
    private messageService: MessageService,
  ) { }

  private showToast(severity: string, cabecera: string, message: string) {
    this.messageService.clear();
    this.messageService.add({ severity, summary: cabecera, detail: message, life: 5000 });
  }

  showSuccess(cabecera: string, message: string) {
    this.showToast('success', cabecera, message);
  }

  showWarn(cabecera: string, message: string) {
    this.showToast('warn', cabecera, message);
  }

  showError(cabecera: string, message: string) {
    this.showToast('error', cabecera, message);
  }



}
