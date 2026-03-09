provider "aws" {
  region = "us-east-2"
}

resource "aws_security_group" "tcs_sg" {
  name        = "tcs-devops-sg"
  description = "Security Group para reto DevOps"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_instance" "devops_server" {
  ami           = "ami-06e3c045d79fd65d9" # Ubuntu 22.04 LTS (x86_64)
  instance_type = "t3.micro"
  key_name      = "devops-key"  #
  vpc_security_group_ids = [aws_security_group.tcs_sg.id]

  user_data = <<-EOF
              #!/bin/bash
              # Instalación de K3s optimizada para t3.micro
              curl -sfL https://get.k3s.io | INSTALL_K3S_EXEC="--disable traefik --disable servicelb" sh -
              # Esperar a que k3s arranque
              sleep 30
              # Ajustar permisos para el usuario ubuntu
              sudo chmod 644 /etc/rancher/k3s/k3s.yaml
              EOF

  tags = { Name = "TCS-Reto-DevOps" }
}

output "instance_public_ip" {
  value = aws_instance.devops_server.public_ip
}