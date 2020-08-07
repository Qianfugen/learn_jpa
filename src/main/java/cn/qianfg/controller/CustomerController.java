package cn.qianfg.controller;

import cn.qianfg.dto.BaseDataResult;
import cn.qianfg.pojo.Customer;
import cn.qianfg.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
@Api(tags = "客户层", value = "客户处")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "查找所有客户",notes = "查找所有客户")
    @GetMapping("/findAll")
    public BaseDataResult findAll() {
        BaseDataResult<List<Customer>> rlt = new BaseDataResult<>();
        rlt.setData(customerService.findAll());
        return rlt;
    }

    @ApiOperation(value = "添加客户",notes = "添加客户")
    @PostMapping("/addCustomer")
    public BaseDataResult addCustomer(Customer customer){
        BaseDataResult<Customer> rlt = new BaseDataResult<>();
        rlt.setData(customerService.save(customer));
        return rlt;
    }

    @ApiOperation(value = "根据id查找客户",notes = "根据id查找客户")
    @GetMapping("/findCustomerById")
    public BaseDataResult findCustomerById(Integer id){
        BaseDataResult<Customer> rlt = new BaseDataResult<>();
        rlt.setData(customerService.findById(id));
        return rlt;
    }

    @ApiOperation(value = "根据id删除客户",notes = "根据id删除客户")
    @DeleteMapping("/deleteCustomerById")
    public BaseDataResult deleteCustomerById(Integer id){
        BaseDataResult rlt = new BaseDataResult();
        customerService.deleteById(id);
        return rlt;
    }

    @ApiOperation(value = "更新客户",notes = "更新客户")
    @PutMapping("/updateCustomer")
    public BaseDataResult<Customer> updateCustomer(Customer customer){
        BaseDataResult<Customer> rlt = new BaseDataResult<>();
        rlt.setData(customerService.update(customer));
        return rlt;
    }

    @ApiOperation(value = "统计客户数量",notes = "统计客户数量")
    @GetMapping("/count")
    public BaseDataResult<Integer> count(){
        BaseDataResult<Integer> rlt = new BaseDataResult<>();
        rlt.setData((int) customerService.count());
        return rlt;
    }

}
