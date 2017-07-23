import socket

HOST = ''  # Symbolic name meaning all available interfaces
PORT = 8081  # Arbitrary non-privileged port
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST, PORT))
s.listen(1)
conn, addr = s.accept()
print('Connected by', addr)

while 1:
  data = conn.recv(1024)
  if not data:
    break
  print(str(data, 'utf-8').rstrip())
  conn.sendall(data)
conn.close()
