// material-ui
// project import
import MainCard from 'components/MainCard';
import {useKeycloak} from "@react-keycloak/web";
import React, {useEffect, useState} from "react";
import {
  SaveOutlined,
  RightCircleOutlined,
  RightCircleFilled
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
import {getAsync} from "../../utils/Axios";

// ==============================|| SAMPLE PAGE ||============================== //

const OrderInfoPage = () => {
  const {keycloak} = useKeycloak();
  const [schools, setSchools] = useState([]);
  const [checked, setChecked] = useState({
    jacket: true,
    shirts: true,
    pants: true
  })

  const [orderInfo, setOrderInfo] = useState({
    schoolId: 1,
    gender: 0,
    seasonType: 0,
    shirtsSize: 95,
    shirtsCount: 1,
    pantsSize: 30,
    pantsCount: 1,
    jacketSize: 100,
    jacketCount: 1

  });

  const searchSchool = async () => {
    const response = await getAsync(`/orders/schools/list`, null,
        keycloak.token)
    setSchools(response);
  };

  const searchProduct = async () => {
    const response = await getAsync(
        `/views/products/schools/${orderInfo.schoolId}/retrieve?gender=${orderInfo.gender}&seasonType=${orderInfo.seasonType}`,
        null, keycloak.token)
    console.info(response);
  };

  const getOrderItemTemplate = () => {

  }

  const save = async () => {
    console.info(orderInfo);
  }

  useEffect(() => {
    searchSchool();
  }, []);

  useEffect(() => {
    searchProduct();
  }, [orderInfo.schoolId]);

  return (
      <MainCard title="주문 정보">
        <FormGroup>
          <Grid item xs={12} sm={12}>
            <Typography variant="h5" gutterBottom>
              인적 사항
            </Typography>
          </Grid>

          <Grid container spacing={3}>
            <Grid item xs={12} sm={3}>
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

            <Grid item xs={12} sm={3}>
              <FormLabel id="gender-radio">Gender</FormLabel>
              <RadioGroup
                  aria-labelledby="gender-radio"
                  defaultValue={orderInfo.gender}
                  name="radio-buttons-group"
                  row={true}
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
                    <Grid item xs={12} sm={4}>
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
                      <Typography variant="h6" gutterBottom>
                        수량
                      </Typography>
                    </Grid>
                    <Grid item xs={12} sm={4}>
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
                    control={<Checkbox icon={<RightCircleOutlined/>}
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
                        <Grid item xs={12} sm={4}>
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
                          <Typography variant="h6" gutterBottom>
                            수량
                          </Typography>
                        </Grid>
                        <Grid item xs={12} sm={4}>
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
                      <Grid item xs={12} sm={4}>
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
                        <Typography variant="h6" gutterBottom>
                          수량
                        </Typography>
                      </Grid>
                      <Grid item xs={12} sm={4}>
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
        </FormGroup>
      </MainCard>
  )
};

export default OrderInfoPage;
