// material-ui
// project import
import MainCard from 'components/MainCard';
import {useKeycloak} from "@react-keycloak/web";
import React, {useEffect, useState} from "react";
import moment from "moment";
import {
  RightCircleFilled,
  RightCircleOutlined,
  SaveOutlined
} from '@ant-design/icons';

import {
  Box,
  Button,
  Checkbox,
  FormControlLabel,
  FormGroup,
  FormLabel,
  Grid,
  InputLabel,
  NativeSelect,
  Radio,
  RadioGroup,
  TextField,
  Typography
} from '@mui/material';
import {getAsync, post} from "../../utils/Axios";

// ==============================|| SAMPLE PAGE ||============================== //


const OrderInfoPage = () => {
  const {keycloak} = useKeycloak();
  const [schools, setSchools] = useState([]);
  const [checked, setChecked] = useState({
    jacket: true,
    shirts: true,
    pants: true
  });
  const [topProductId, setTopProductId] = useState(0);
  const [downProductId, setDownProductId] = useState(0);
  const [jacketProductId, setJacketProductId] = useState(0);
  const initOrderInfo = {
    schoolId: 1,
    gender: 0,
    seasonType: 0,
    shirtsSize: 95,
    shirtsCount: 1,
    pantsSize: 30,
    pantsCount: 1,
    jacketSize: 100,
    jacketCount: 1,
    studentName: "",
    phone: ""
  }
  const [orderInfo, setOrderInfo] = useState(initOrderInfo);

  const searchSchool = async () => {
    const response = await getAsync(`/orders/schools/list`, null,
        keycloak.token)
    setSchools(response);
  };

  const searchProduct = async () => {
    const response = await getAsync(
        `/products/product-mgmt/schools/${orderInfo.schoolId}/list?gender=${orderInfo.gender}&seasonType=${orderInfo.seasonType}`,
        null, keycloak.token)

    // todo response sorting by typeCode
    response.map((product) => {
      let typeCode = parseInt(product.typeCode);
      if (typeCode >= 10 && typeCode < 20) {
        setTopProductId(product.id)
      } else if (typeCode >= 20 && typeCode < 30) {
        setDownProductId(product.id)
      } else if (typeCode >= 30 && typeCode < 40) {
        setJacketProductId(product.id)
      }
    });
  };

  const save = async (e) => {

    if(orderInfo.studentName === "" || orderInfo.phone === ""){
      return;
    }

    let arr = [];
    if (checked.shirts) {
      arr.push({
        "productId": topProductId,
        "size": orderInfo.shirtsSize,
        "quantity": orderInfo.shirtsCount
      })
    }
    if (checked.pants) {
      arr.push({
        "productId": downProductId,
        "size": orderInfo.pantsSize,
        "quantity": orderInfo.pantsCount
      })
    }
    if (checked.jacketSize) {
      arr.push({
        "productId": jacketProductId,
        "size": orderInfo.jacketSize,
        "quantity": orderInfo.jacketCount
      })
    }
    let body = {
      studentName: orderInfo.studentName,
      phone: orderInfo.phone,
      schoolId: orderInfo.schoolId,
      gender: orderInfo.gender,
      seasonType: orderInfo.seasonType,
      orderDetailList: arr
    }

    const response = await post(
        `/views/orders`,
        body, keycloak.token).then(r => {});
    e.refresh();
  }

  useEffect(() => {
    searchSchool();
  }, []);

  useEffect(() => {
    searchProduct();
  }, [orderInfo.schoolId, orderInfo.gender, orderInfo.seasonType]);

  return (
      <MainCard title="주문 정보">
        <FormGroup>
          <form>
            <Grid item xs={12} sm={12}>
              <Typography variant="h5" gutterBottom>
                인적 사항
              </Typography>
            </Grid>

            <Grid container spacing={3}>
              <Grid item xs={12} sm={2}>
                <InputLabel variant="standard" htmlFor="school">학교</InputLabel>
                <NativeSelect
                    fullWidth
                    inputProps={{
                      name: 'school',
                      id: 'school',
                    }}
                    onChange={(e) => {
                      setOrderInfo({...orderInfo, schoolId: e.target.value});
                    }}
                >
                  {schools.map((school) => <option key={school.id}
                                                   value={school.id}>{school.name}</option>)}
                </NativeSelect>
              </Grid>

              <Grid item xs={12} sm={2}>
                <FormLabel id="seasonType-radio">Season</FormLabel>
                <RadioGroup
                    aria-labelledby="seasonType-radio"
                    defaultValue={orderInfo.seasonType}
                    name="radio-buttons-group"
                    row={true}
                    onChange={event => setOrderInfo(
                        {...orderInfo, seasonType: event.target.value})}
                >
                  <FormControlLabel value="0" control={<Radio/>} label="Summer"/>
                  <FormControlLabel value="1" control={<Radio/>} label="Winter"/>
                </RadioGroup>
              </Grid>

              <Grid item xs={12} sm={2}>
                <FormLabel id="gender-radio">Gender</FormLabel>
                <RadioGroup
                    aria-labelledby="gender-radio"
                    defaultValue={orderInfo.gender}
                    name="radio-buttons-group"
                    row={true}
                    onChange={event => setOrderInfo(
                        {...orderInfo, gender: event.target.value})}
                >
                  <FormControlLabel value="0" control={<Radio/>} label="Male"/>
                  <FormControlLabel value="1" control={<Radio/>} label="Female"/>
                </RadioGroup>
              </Grid>

              <Grid item xs={12} sm={3}>
                <TextField
                    required
                    id="studentName"
                    name="name"
                    label="이름"
                    error={orderInfo.studentName === "" ? true : false}
                    fullWidth
                    variant="outlined"
                    onChange={(e) => {
                      setOrderInfo({...orderInfo, studentName: e.target.value});
                    }}
                />
              </Grid>
              <Grid item xs={12} sm={3}>
                <TextField
                    required
                    id="phone"
                    name="phone"
                    error={orderInfo.phone === "" ? true : false}
                    label="전화번호"
                    fullWidth
                    variant="outlined"
                    onChange={(e) => {
                      setOrderInfo({...orderInfo, phone: e.target.value});
                    }}
                />
              </Grid>

              <Grid item xs={12} sm={12}>
                <Typography variant="h5" gutterBottom>
                  주문 사항
                </Typography>
              </Grid>
              <Grid item xs={12} sm={2}>
                <Typography variant="h6" gutterBottom>
                  <FormControlLabel
                      control={<Checkbox defaultChecked
                                         icon={<RightCircleOutlined/>}
                                         checkedIcon={<RightCircleFilled/>}
                                         onChange={e => setChecked(
                                             {
                                               ...checked,
                                               shirts: e.target.checked
                                             })}/>}
                      label=""
                  />
                  Shirts
                </Typography>
              </Grid>
              {
                checked.shirts ? (
                    <>
                      <Grid item xs={12} sm={2}>
                        <Box display="flex" justifyContent="flex-end">
                          <Typography variant="h6" gutterBottom>
                            사이즈
                          </Typography>
                        </Box>
                      </Grid>
                      <Grid item xs={12} sm={3}>
                        <NativeSelect
                            fullWidth
                            label={"shirts-size"}
                            defaultValue={orderInfo.shirtsSize}
                            inputProps={{
                              name: 'shirts-size',
                              id: 'shirts-size',
                            }}
                            onChange={(e) => {
                              setOrderInfo(
                                  {...orderInfo, shirtsSize: e.target.value});
                            }}
                        >
                          <option key={85} value={85}>85</option>
                          <option key={90} value={90}>90</option>
                          <option key={95} value={95}>95</option>
                          <option key={100} value={100}>100</option>
                          <option key={105} value={100}>105</option>
                          <option key={110} value={100}>110</option>
                          <option key={115} value={100}>115</option>
                        </NativeSelect>
                      </Grid>
                      <Grid item xs={12} sm={2}>
                        <Box display="flex" justifyContent="flex-end">
                          <Typography variant="h6" gutterBottom>
                            수량
                          </Typography>
                        </Box>
                      </Grid>
                      <Grid item xs={12} sm={3}>
                        <NativeSelect
                            fullWidth
                            label={"shirts-count"}
                            defaultValue={orderInfo.shirtsCount}
                            inputProps={{
                              name: 'shirts-count',
                              id: 'shirts-count',
                            }}
                            onChange={(e) => {
                              setOrderInfo(
                                  {...orderInfo, shirtsCount: e.target.value});
                            }}
                        >
                          <option key={1} value={1}>1</option>
                          <option key={2} value={2}>2</option>
                          <option key={3} value={3}>3</option>
                          <option key={4} value={4}>4</option>
                          <option key={5} value={5}>5</option>
                        </NativeSelect>
                      </Grid>
                    </>
                ) : (
                    <Grid item xs={12} sm={10}></Grid>
                )
              }

              <Grid item xs={12} sm={2}>
                <Typography variant="h6" gutterBottom>
                  <FormControlLabel
                      control={<Checkbox defaultChecked
                                         icon={<RightCircleOutlined/>}
                                         checkedIcon={<RightCircleFilled/>}
                                         onChange={e => setChecked(
                                             {
                                               ...checked,
                                               pants: e.target.checked
                                             })}/>}
                      label=""
                  />
                  Pants / Skirt
                </Typography>
              </Grid>
              {
                checked.pants ?
                    (
                        <>
                          <Grid item xs={12} sm={2}>
                            <Box display="flex" justifyContent="flex-end">
                              <Typography variant="h6" gutterBottom>
                                사이즈
                              </Typography>
                            </Box>
                          </Grid>
                          <Grid item xs={12} sm={3}>
                            <NativeSelect
                                fullWidth
                                label={"pants-size"}
                                defaultValue={orderInfo.pantsSize}
                                inputProps={{
                                  name: 'pants-size',
                                  id: 'pants-size',
                                }}
                                onChange={(e) => {
                                  setOrderInfo(
                                      {...orderInfo, pantsSize: e.target.value});
                                }}
                            >
                              <option key={26} value={26}>26</option>
                              <option key={28} value={28}>28</option>
                              <option key={30} value={30}>30</option>
                              <option key={32} value={32}>32</option>
                              <option key={34} value={34}>34</option>
                              <option key={36} value={36}>36</option>
                              <option key={38} value={38}>38</option>
                            </NativeSelect>
                          </Grid>
                          <Grid item xs={12} sm={2}>
                            <Box display="flex" justifyContent="flex-end">
                              <Typography variant="h6" gutterBottom>
                                수량
                              </Typography>
                            </Box>
                          </Grid>
                          <Grid item xs={12} sm={3}>
                            <NativeSelect
                                fullWidth
                                label={"pants-count"}
                                defaultValue={orderInfo.pantsCount}
                                inputProps={{
                                  name: 'pants-count',
                                  id: 'pants-count',
                                }}
                                onChange={(e) => {
                                  setOrderInfo(
                                      {...orderInfo, pantsCount: e.target.value});
                                }}
                            >
                              <option key={1} value={1}>1</option>
                              <option key={2} value={2}>2</option>
                              <option key={3} value={3}>3</option>
                              <option key={4} value={4}>4</option>
                              <option key={5} value={5}>5</option>
                            </NativeSelect>
                          </Grid>
                        </>
                    )
                    :
                    (
                        <Grid item xs={12} sm={10}></Grid>
                    )
              }
              <Grid item xs={12} sm={2}>
                <Typography variant="h6" gutterBottom>
                  <FormControlLabel
                      control={<Checkbox defaultChecked
                                         icon={<RightCircleOutlined/>}
                                         checkedIcon={<RightCircleFilled/>}
                                         onChange={e => setChecked(
                                             {
                                               ...checked,
                                               jacket: e.target.checked
                                             })}/>}
                      label=""
                  />
                  Jacket
                </Typography>
              </Grid>

              {checked.jacket ?
                  (
                      <>
                        <Grid item xs={12} sm={2}>
                          <Box display="flex" justifyContent="flex-end">
                            <Typography variant="h6" gutterBottom>
                              사이즈
                            </Typography>
                          </Box>
                        </Grid>
                        <Grid item xs={12} sm={3}>
                          <NativeSelect
                              fullWidth
                              label={"jacket-size"}
                              defaultValue={orderInfo.jacketSize}
                              inputProps={{
                                name: 'jacket-size',
                                id: 'jacket-size',
                              }}
                              onChange={(e) => {
                                setOrderInfo(
                                    {...orderInfo, jacketSize: e.target.value});
                              }}
                          >
                            <option key={85} value={85}>85</option>
                            <option key={90} value={90}>90</option>
                            <option key={95} value={95}>95</option>
                            <option key={100} value={100}>100</option>
                            <option key={105} value={100}>105</option>
                            <option key={110} value={100}>110</option>
                            <option key={115} value={100}>115</option>
                          </NativeSelect>
                        </Grid>
                        <Grid item xs={12} sm={2}>
                          <Box display="flex" justifyContent="flex-end">
                            <Typography variant="h6" gutterBottom>
                              수량
                            </Typography>
                          </Box>
                        </Grid>
                        <Grid item xs={12} sm={3}>
                          <NativeSelect
                              fullWidth
                              label={"jacket-count"}
                              defaultValue={orderInfo.jacketCount}
                              inputProps={{
                                name: 'jacket-count',
                                id: 'jacket-count',
                              }}
                              onChange={(e) => {
                                setOrderInfo(
                                    {...orderInfo, jacketCount: e.target.value});
                              }}
                          >
                            <option key={1} value={1}>1</option>
                            <option key={2} value={2}>2</option>
                            <option key={3} value={3}>3</option>
                            <option key={4} value={4}>4</option>
                            <option key={5} value={5}>5</option>
                          </NativeSelect>
                        </Grid>
                      </>
                  )
                  :
                  (
                      <Grid item xs={12} sm={10}></Grid>
                  )
              }

            </Grid>
            <Grid item xs={12} sx={{mt: 3}}>
              <Box display="flex" justifyContent="flex-end">
                <Button
                    type="submit"
                    variant="contained"
                    color="info"
                    onClick={save}
                >
                  저장 &nbsp;
                  <SaveOutlined/>
                </Button>
              </Box>
            </Grid>
          </form>
        </FormGroup>
      </MainCard>
  )
};

export default OrderInfoPage;
