#!/usr/bin/python3
import socket
import select
import sys
import time


host = '203.250.133.88'
port = 16332
BUFSIZ = 1024
BACKLOG = 5
connections = {}

class User:
    def __init__(self, ip):
        self.ip = ip
        self.name = None


def broadcast(msg, nickname=""):
    if not nickname:
        nickname = "Server"

    for sock, user in connections.items():
        message = nickname + ': ' + msg.decode()
        if user.name is not None:
            sock.sendall(message.encode())
    return


conn_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
conn_sock.bind((host, port))
conn_sock.listen(BACKLOG)

rlist = [sys.stdin, conn_sock]

while True:
    print("Waiting for messages...")
    readable, writable, exceptional = select.select(rlist, [], rlist)
    for sock in readable:
        if sock is sys.stdin:
            cmd = input()
            if cmd == 'quit':
                print('Bye!')
                sys.exit(0)
            elif cmd == 'user':
                for user in connections.values():
                    print('%s -> %s' % (user.name, user.ip))

        elif sock is conn_sock:
            data_sock, client_addr = conn_sock.accept()
            rlist.append(data_sock)
            print("Client from {} has connected...".format(client_addr[0]))
            joined_users = ''

            for user in connections.values():
                if user.name is not None:
                    if len(joined_users) > 0:
                        joined_users += ', '
                    joined_users += user.name


            if len(joined_users) > 0:
                message = "Users %s are in chatroom.\n" % joined_users
                data_sock.send(message.encode())
                time.sleep(2)

            data_sock.send("Hi there, Please send me your nickname@@".encode())
            new_user = User(client_addr[0])
            connections[data_sock] = new_user
        else:
            message = sock.recv(BUFSIZ)
            user = connections.get(sock)
            print('message from %s -> %s' % (user.name, message.decode()))
            if user.name is None:
                name = message.decode()
                welcome = "Welcome {}. if want to quit, send me 'Bye!'".format(name)
                sock.send(welcome.encode())

                message = "{} has joined...".format(name)
                broadcast(message.encode())

                user.name = name
            else:
                if message.decode() == "Bye!":
                    sock.close()
                    rlist.remove(sock)
                    del connections[sock]
                    broadcast("{} has left...".format(user.name).encode())
                    break
                else:
                    broadcast(message, user.name)