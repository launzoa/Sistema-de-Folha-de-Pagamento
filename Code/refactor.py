import os
import re

ROOT_DIR = "/home/launzoa/GitHub/Sistema-de-Folha-de-Pagamento/Code/src"

REPLACEMENTS = {
    "com.sfp.core.domain.Funcionario": "com.sfp.funcionario.domain.Funcionario",
    "com.sfp.core.domain.FolhaDePonto": "com.sfp.funcionario.domain.FolhaDePonto",
    "com.sfp.core.domain.FuncionarioRepository": "com.sfp.funcionario.domain.FuncionarioRepository",
    "com.sfp.infrastructure.persistence.MySQLFuncionarioRepository": "com.sfp.funcionario.infrastructure.persistence.MySQLFuncionarioRepository",
    "com.sfp.core.domain.Empresa": "com.sfp.empresa.domain.Empresa",
    "com.sfp.core.domain.EnderecoEmpresa": "com.sfp.empresa.domain.EnderecoEmpresa",
    "com.sfp.core.domain.Usuario": "com.sfp.usuario.domain.Usuario",
    "com.sfp.core.domain.RegistroAuditoria": "com.sfp.auditoria.domain.RegistroAuditoria",
    "com.sfp.core.domain.Rubrica": "com.sfp.rubrica.domain.Rubrica",
    "com.sfp.core.ConexaoBD": "com.sfp.core.database.ConexaoBD",
    "com.sfp.folha.ui.GerenciadorTema": "com.sfp.core.ui.GerenciadorTema",
    "com.sfp.folha.ui.MainController": "com.sfp.core.ui.MainController",
    "com.sfp.folha.ui.CadastroFuncionarioController": "com.sfp.funcionario.ui.CadastroFuncionarioController",
    "com.sfp.folha.ui.FormEmpresa": "com.sfp.empresa.ui.FormEmpresa",
    "com.sfp.folha.ui.FormEndereco": "com.sfp.empresa.ui.FormEndereco",
    "com.sfp.folha.ui.FormRubrica": "com.sfp.rubrica.ui.FormRubrica",
    "com.sfp.folha.ui.FormUsuario": "com.sfp.usuario.ui.FormUsuario",
    "com.sfp.folha.ui.TelaLogin": "com.sfp.autenticacao.ui.TelaLogin",
    "com.sfp.folha.ui.TelaLog": "com.sfp.auditoria.ui.TelaLog",
    "com.sfp.folha.ui.TelaEmpresa": "com.sfp.empresa.ui.TelaEmpresa",
    "com.sfp.folha.ui.TelaUsuario": "com.sfp.usuario.ui.TelaUsuario",
    "com.sfp.folha.ui.TelaRubrica": "com.sfp.rubrica.ui.TelaRubrica",
    "com.sfp.folha.ui.TelaDashboard": "com.sfp.core.ui.TelaDashboard",
    "com.sfp.folha.ui.TelaFuncionario": "com.sfp.funcionario.ui.TelaFuncionario",
    
    "/com/sfp/folha/ui/TelaLogin.fxml": "/com/sfp/autenticacao/ui/TelaLogin.fxml",
    "/com/sfp/folha/ui/MainView.fxml": "/com/sfp/core/ui/MainView.fxml",
    "/com/sfp/folha/ui/style.css": "/com/sfp/core/ui/style.css",
    "/com/sfp/folha/ui/gato.png": "/com/sfp/core/ui/gato.png",
    "/com/sfp/folha/ui/TelaFuncionario.fxml": "/com/sfp/funcionario/ui/TelaFuncionario.fxml",
    "/com/sfp/folha/ui/CadastroFuncionarioView.fxml": "/com/sfp/funcionario/ui/CadastroFuncionarioView.fxml",
    "/com/sfp/folha/ui/TelaEmpresa.fxml": "/com/sfp/empresa/ui/TelaEmpresa.fxml",
    "/com/sfp/folha/ui/FormEmpresa.fxml": "/com/sfp/empresa/ui/FormEmpresa.fxml",
    "/com/sfp/folha/ui/FormEndereco.fxml": "/com/sfp/empresa/ui/FormEndereco.fxml",
    "/com/sfp/folha/ui/TelaUsuario.fxml": "/com/sfp/usuario/ui/TelaUsuario.fxml",
    "/com/sfp/folha/ui/FormUsuario.fxml": "/com/sfp/usuario/ui/FormUsuario.fxml",
    "/com/sfp/folha/ui/TelaRubrica.fxml": "/com/sfp/rubrica/ui/TelaRubrica.fxml",
    "/com/sfp/folha/ui/FormRubrica.fxml": "/com/sfp/rubrica/ui/FormRubrica.fxml",
    "/com/sfp/folha/ui/TelaLog.fxml": "/com/sfp/auditoria/ui/TelaLog.fxml",
    "/com/sfp/folha/ui/TelaDashboard.fxml": "/com/sfp/core/ui/TelaDashboard.fxml"
}

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
        
    modified = False
    
    # 1. Update global replacements
    for old, new in REPLACEMENTS.items():
        if old in content:
            content = content.replace(old, new)
            modified = True
            
    # 2. Update package declaration for Java files
    if filepath.endswith('.java'):
        # Extract the correct package path from the file's path
        parts = filepath.split('/src/main/java/')
        if len(parts) == 2:
            pkg_path = os.path.dirname(parts[1])
            correct_pkg = pkg_path.replace('/', '.')
            
            # Find the package line
            lines = content.split('\n')
            for i, line in enumerate(lines):
                if line.startswith('package '):
                    old_pkg_line = line.strip()
                    new_pkg_line = f"package {correct_pkg};"
                    if old_pkg_line != new_pkg_line:
                        lines[i] = new_pkg_line
                        content = '\n'.join(lines)
                        modified = True
                    break
        else:
            # Check for tests
            parts_test = filepath.split('/src/test/java/')
            if len(parts_test) == 2:
                pkg_path = os.path.dirname(parts_test[1])
                correct_pkg = pkg_path.replace('/', '.')
                lines = content.split('\n')
                for i, line in enumerate(lines):
                    if line.startswith('package '):
                        lines[i] = f"package {correct_pkg};"
                        content = '\n'.join(lines)
                        modified = True
                        break

    if modified:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f"Updated: {filepath}")

for root, _, files in os.walk(ROOT_DIR):
    for file in files:
        if file.endswith('.java') or file.endswith('.fxml'):
            process_file(os.path.join(root, file))
