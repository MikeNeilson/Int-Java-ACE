/*open*/ module my.service.module {
  exports myservice to my.client.module, other, bad;
  opens myservice to my.client.module, my.not.yet.written;
  provides myservice.MyServiceIf with myserviceimplpackage.MyServiceImpl;
}