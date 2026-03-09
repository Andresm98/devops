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
  ami           = "ami-06e3c045d79fd65d9" #
  instance_type = "c7i-flex.large"        #
  key_name      = "devopsr3-key"
  vpc_security_group_ids = [aws_security_group.tcs_sg.id]

  user_data = <<-EOF
              #!/bin/bash
              # Instalación más limpia, omitiendo componentes pesados
              curl -sfL https://get.k3s.io | INSTALL_K3S_EXEC="--disable traefik --disable servicelb --kubelet-arg='eviction-hard=memory.available<500Mi'" sh -
              EOF

  tags = { Name = "TCS-Reto-DevOps-Pro" }
}

output "instance_public_ip" {
  value = aws_instance.devops_server.public_ip
}